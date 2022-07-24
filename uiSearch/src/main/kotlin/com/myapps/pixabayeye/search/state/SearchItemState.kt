package com.myapps.pixabayeye.search.state

data class SearchItemState(
    val imageId: Long,
    val userName: String,
    val tags: List<String>,
    val previewUrl: String,
)
