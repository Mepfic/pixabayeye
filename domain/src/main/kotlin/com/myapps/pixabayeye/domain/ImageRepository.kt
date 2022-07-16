package com.myapps.pixabayeye.domain

import com.myapps.pixabayeye.data.HitResponse
import com.myapps.pixabayeye.data.ImagesResponse

interface ImageRepository {
    suspend fun getImages(query: String) : ImagesResponse
    suspend fun getImageById(id: Long) : HitResponse
}