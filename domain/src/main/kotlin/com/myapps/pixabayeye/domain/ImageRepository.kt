package com.myapps.pixabayeye.domain

import androidx.paging.PagingData
import com.myapps.pixabayeye.domain.model.HitModel
import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    fun getImages(query: String) : Flow<PagingData<HitModel>>
    suspend fun getImageById(id: Long) : HitModel
}