package com.myapps.pixabayeye.domain

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.myapps.pixabayeye.data.database.dao.ImagesDao
import com.myapps.pixabayeye.data.datasource.ImagesRemoteMediator
import com.myapps.pixabayeye.domain.model.HitModel
import com.myapps.pixabayeye.domain.model.mapEntityToHitModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@ExperimentalPagingApi
class ImageRepositoryImpl @Inject constructor(
    private val imagesRemoteMediatorFactory: ImagesRemoteMediator.Factory,
    private val imagesDao: ImagesDao,
) : ImageRepository {

    override fun getImages(query: String): Flow<PagingData<HitModel>> =
        Pager(
            config = getPagingConfig(),
            remoteMediator = imagesRemoteMediatorFactory.create(query),
            pagingSourceFactory = { imagesDao.getImagesByQuery() }
        )
            .flow
            .map { it.map(mapEntityToHitModel) }

    override suspend fun getImageById(id: Long): HitModel =
        imagesDao.getHitById(id).let(mapEntityToHitModel)

    private fun getPagingConfig() =
        PagingConfig(
            pageSize = PAGE_CAPACITY,
            initialLoadSize = PAGE_CAPACITY,
            enablePlaceholders = true,
            maxSize = BUFFER_CAPACITY
        )

    companion object {
        private const val PAGE_CAPACITY = 50
        private const val BUFFER_CAPACITY = 150
    }
}