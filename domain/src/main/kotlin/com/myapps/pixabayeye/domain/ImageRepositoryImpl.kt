package com.myapps.pixabayeye.domain

import com.myapps.pixabayeye.data.HitResponse
import com.myapps.pixabayeye.data.ImagesResponse
import com.myapps.pixabayeye.data.MainNetworkApi
import com.myapps.pixabayeye.data.cache.MemoryCache
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val mainNetworkApi: MainNetworkApi,
    private val memoryCache: MemoryCache
): ImageRepository {

    override suspend fun getImages(query: String): ImagesResponse {
        val response = mainNetworkApi.getImages(
            "28584276-6da744396fde61c4822dfa505",
            query,
            20,
            1
        )
        memoryCache.updateCache(response.hits)
        return response
    }


    override suspend fun getImageById(id: Long): HitResponse =
        requireNotNull(memoryCache.getHitFromCache(id))
}