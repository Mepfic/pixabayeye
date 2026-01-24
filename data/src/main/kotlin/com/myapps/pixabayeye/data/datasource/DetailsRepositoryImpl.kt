package com.myapps.pixabayeye.data.datasource

import com.myapps.pixabayeye.data.database.dao.ImagesDao
import com.myapps.pixabayeye.domain.DetailsRepository
import com.myapps.pixabayeye.domain.model.HitModel
import javax.inject.Inject

class DetailsRepositoryImpl @Inject constructor(
    private val imagesDao: ImagesDao,
) : DetailsRepository {

    override suspend fun getImageById(id: Long): HitModel =
        imagesDao.getHitById(id).let(mapEntityToHitModel)
}