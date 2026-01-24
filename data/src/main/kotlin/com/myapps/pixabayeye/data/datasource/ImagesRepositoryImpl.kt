package com.myapps.pixabayeye.data.datasource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.myapps.pixabayeye.data.database.dao.ImagesDao
import com.myapps.pixabayeye.domain.ImagesRepository
import com.myapps.pixabayeye.domain.model.HitModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class ImagesRepositoryImpl @Inject constructor(
    private val imagesRemoteMediatorFactory: ImagesRemoteMediator.Factory,
    private val imagesDao: ImagesDao,
) : ImagesRepository {

    override fun getImages(query: String): Flow<PagingData<HitModel>> =
        Pager(
            config = getPagingConfig(),
            remoteMediator = imagesRemoteMediatorFactory.create(query),
            pagingSourceFactory = { imagesDao.getImagesByQuery(query) }
        )
            .flow
            .map { it.map(mapEntityToHitModel) }

    private fun getPagingConfig() =
        PagingConfig(
            pageSize = PAGE_CAPACITY,
            initialLoadSize = PAGE_CAPACITY,
            prefetchDistance = PREFETCH_DISTANCE,
            enablePlaceholders = false,
            maxSize = BUFFER_CAPACITY
        )

    companion object {
        private const val PAGE_CAPACITY = 20
        private const val PREFETCH_DISTANCE = 8
        private const val BUFFER_CAPACITY = 60
    }
}