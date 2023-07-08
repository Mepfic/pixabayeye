package com.myapps.pixabayeye.domain

import androidx.paging.ExperimentalPagingApi
import com.myapps.pixabayeye.data.database.dao.ImagesDao
import com.myapps.pixabayeye.domain.model.HitModel
import com.myapps.pixabayeye.domain.model.mapEntityToHitModel
import javax.inject.Inject

@ExperimentalPagingApi
class DetailsRepositoryImpl @Inject constructor(
    private val imagesDao: ImagesDao,
) : DetailsRepository {

    override suspend fun getImageById(id: Long): HitModel =
        imagesDao.getHitById(id).let(mapEntityToHitModel)
}
