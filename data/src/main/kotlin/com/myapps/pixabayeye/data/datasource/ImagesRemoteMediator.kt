package com.myapps.pixabayeye.data.datasource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.myapps.pixabayeye.data.database.AppDatabase
import com.myapps.pixabayeye.data.database.dao.ImagesDao
import com.myapps.pixabayeye.data.database.model.HitEntity
import com.myapps.pixabayeye.data.network.MainNetworkApi
import com.myapps.pixabayeye.data.network.model.ImagesResponse
import com.myapps.pixabayeye.data.network.model.mapResponseToHitEntity
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import java.io.IOException
import retrofit2.HttpException

@ExperimentalPagingApi
class ImagesRemoteMediator @AssistedInject constructor(
    private val database: AppDatabase,
    private val mainNetworkApi: MainNetworkApi,
    @Assisted private val query: String
) : RemoteMediator<Int, HitEntity>() {

    private val imagesDao: ImagesDao = database.imagesDao()

    private var pageIndex = 1

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, HitEntity>
    ): MediatorResult {
        pageIndex = getPageIndex(loadType).coerceAtLeast(1)
        val pageSize = state.config.pageSize

        try {
            if (loadType == LoadType.PREPEND)
                return MediatorResult.Success(endOfPaginationReached = true)

            val data = fetchImages(pageSize, pageIndex)

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    imagesDao.clearAll()
                }
                imagesDao.insertAll(data.hits.map(mapResponseToHitEntity))
            }

            return MediatorResult.Success(
                endOfPaginationReached =
                data.hits.size < pageSize
                        || data.totalHits <= pageIndex * pageSize
                        || loadType == LoadType.PREPEND
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
            key = "28584276-6da744396fde61c4822dfa505",
            query = query,
            perPage = pageSize,
            page = pageNumber
        )

    @AssistedFactory
    interface Factory {
        fun create(query: String): ImagesRemoteMediator
    }
}