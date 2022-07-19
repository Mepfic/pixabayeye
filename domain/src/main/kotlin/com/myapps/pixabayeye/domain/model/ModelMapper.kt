package com.myapps.pixabayeye.domain.model

import com.myapps.pixabayeye.data.database.model.HitEntity

internal val mapEntityToHitModel = { entity: HitEntity ->
    HitModel(
        imageId = entity.imageId,
        userId = entity.userId,
        userName = entity.userName,
        tags = entity.tags,
        likes = entity.likes,
        downloads = entity.downloads,
        comments = entity.comments,
        previewUrl = entity.previewUrl,
        middleImageUrl = entity.middleImageUrl,
        largeImageUrl = entity.largeImageUrl
    )
}