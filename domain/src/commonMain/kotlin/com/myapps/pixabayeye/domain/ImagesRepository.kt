package com.myapps.pixabayeye.domain

import androidx.paging.PagingData
import com.myapps.pixabayeye.domain.model.HitModel
import kotlinx.coroutines.flow.Flow

interface ImagesRepository {
    fun getImages(query: String): Flow<PagingData<HitModel>>
}
