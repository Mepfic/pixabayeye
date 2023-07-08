package com.myapps.pixabayeye.search.state

import com.myapps.pixabayeye.domain.model.HitModel
import com.myapps.pixabayeye.domain.util.tagsToList

internal val mapToSearchItemState = { model: HitModel ->
    SearchItemState(
        imageId = model.imageId,
        userName = model.userName,
        tags = model.tags.tagsToList(),
        previewUrl = model.previewUrl
    )
}
