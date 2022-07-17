package com.myapps.pixabayeye.data.network.model

import com.myapps.pixabayeye.data.database.model.HitEntity

internal val mapResponseToHitEntity = { response: HitResponse ->
    HitEntity(
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
