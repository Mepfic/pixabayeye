package com.myapps.pixabayeye.data.datasource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.myapps.pixabayeye.data.BuildConfig
import com.myapps.pixabayeye.data.database.AppDatabase
import com.myapps.pixabayeye.data.database.dao.ImagesDao
import com.myapps.pixabayeye.data.database.dao.SearchDao
import com.myapps.pixabayeye.data.database.model.HitEntity
import com.myapps.pixabayeye.data.database.model.SearchEntity
import com.myapps.pixabayeye.data.database.model.SearchQueryEntity
import com.myapps.pixabayeye.data.network.MainNetworkApi
import com.myapps.pixabayeye.data.network.model.ImagesResponse
import com.myapps.pixabayeye.data.network.model.mapResponseToHitEntity
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class ImagesRemoteMediator @AssistedInject constructor(
    private val database: AppDatabase,
    private val mainNetworkApi: MainNetworkApi,
    @Assisted private val query: String,
) : RemoteMediator<Int, HitEntity>() {

    private val imagesDao: ImagesDao = database.imagesDao()
    private val searchDao: SearchDao = database.searchDao()

    private var pageIndex = 1
    private var isCacheValid = false

    override suspend fun initialize(): InitializeAction {
        val cachedQuery = searchDao.getQuery(query)
        val hasCachedData = imagesDao.getCachedCount(query) > 0

        // Check if cache is valid (< 6 hours old)
        val now = System.currentTimeMillis() / 1000
        isCacheValid = cachedQuery?.let { (now - it.timestamp) < SIX_HOURS } ?: false

        return if (isCacheValid && hasCachedData) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    @Suppress("ReturnCount")
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, HitEntity>,
    ): MediatorResult {
        try {
            if (loadType == LoadType.PREPEND) {
                return MediatorResult.Success(endOfPaginationReached = true)
            }

            val cachedQuery = searchDao.getQuery(query)
            pageIndex = when {
                loadType == LoadType.REFRESH -> 1
                isCacheValid && cachedQuery != null -> {
                    // Start from next page after cached
                    if (loadType == LoadType.APPEND) {
                        cachedQuery.lastFetchedPage + 1
                    } else {
                        getPageIndex(loadType).coerceAtLeast(1)
                    }
                }

                else -> getPageIndex(loadType).coerceAtLeast(1)
            }

            val pageSize = state.config.pageSize

            // Check if page already in cache
            if (isCacheValid &&
                loadType == LoadType.APPEND &&
                cachedQuery != null &&
                pageIndex <= cachedQuery.lastFetchedPage
            ) {
                // DB already has the data, let PagingSource handle it
                return MediatorResult.Success(endOfPaginationReached = false)
            }

            val data = fetchImages(pageSize, pageIndex)

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    searchDao.clearSearch(query)
                    isCacheValid = false
                }
                searchDao.insertAll(data.hits.map { SearchEntity(it.imageId, query) })
                imagesDao.insertAll(data.hits.map(mapResponseToHitEntity))
                searchDao.insertQuery(
                    SearchQueryEntity(
                        queryText = query,
                        timestamp = System.currentTimeMillis() / 1000,
                        lastFetchedPage = pageIndex
                    )
                )
            }

            return MediatorResult.Success(
                endOfPaginationReached =
                    data.hits.size < pageSize ||
                            data.totalHits <= pageIndex * pageSize
            )
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }

    private fun getPageIndex(loadType: LoadType): Int {
        pageIndex = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> --pageIndex
            LoadType.APPEND -> ++pageIndex
        }
        return pageIndex
    }

    private suspend fun fetchImages(pageSize: Int, pageNumber: Int): ImagesResponse =
        mainNetworkApi.getImages(
            key = BuildConfig.API_KEY,
            query = query,
            perPage = pageSize,
            page = pageNumber
        )

    @AssistedFactory
    interface Factory {
        fun create(query: String): ImagesRemoteMediator
    }

    companion object {
        private const val SIX_HOURS = 21600
    }
}
