package com.myapps.pixabayeye.data.test

import com.myapps.pixabayeye.data.network.MainNetworkApi
import com.myapps.pixabayeye.data.network.model.ImagesResponse

class SubNetworkApi : MainNetworkApi {
    override suspend fun getImages(
        key: String,
        query: String,
        perPage: Int,
        page: Int,
    ): ImagesResponse {
        return ImagesResponse(
            total = 1000,
            totalHits = 500,
            hits = SubHitsFactory.createHits(perPage)
        )
    }
}
