package com.myapps.pixabayeye.domain

import com.myapps.pixabayeye.domain.model.HitModel

interface DetailsRepository {
    suspend fun getImageById(id: Long): HitModel
}
