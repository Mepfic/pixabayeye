package com.myapps.pixabayeye.domain.model

import com.myapps.pixabayeye.data.HitResponse
import com.myapps.pixabayeye.data.ImagesResponse

internal val mapToHitsModel = { response: ImagesResponse ->
    HitsModel(
        total = response.total,
        totalHits = response.totalHits,
        hits = response.hits.map(mapToHitModel)
    )
}

internal val mapToHitModel = { response: HitResponse ->
    HitModel(
        imageId = response.imageId,
        userId = response.userId,
        userName = response.userName,
        tags = response.tags,
        likes = response.likes,
        downloads = response.downloads,
        previewUrl = response.previewUrl,
        middleImageUrl = response.middleImageUrl,
        largeImageUrl = response.largeImageUrl
    )
}