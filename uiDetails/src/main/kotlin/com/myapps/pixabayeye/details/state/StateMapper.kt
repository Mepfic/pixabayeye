package com.myapps.pixabayeye.details.state

import com.myapps.pixabayeye.domain.model.HitModel
import com.myapps.pixabayeye.domain.util.tagsToList

internal val mapToDetailsState = { model: HitModel ->
    DetailsState(
        userName = model.userName,
        tags = model.tags.tagsToList(),
        likes = model.likes,
        downloads = model.downloads,
        comments = model.comments,
        previewUrl = model.previewUrl,
        middleImageUrl = model.middleImageUrl,
        largeImageUrl = model.largeImageUrl
    )
}
