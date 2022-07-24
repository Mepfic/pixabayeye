package com.myapps.pixabayeye.details.state

data class DetailsState(
    val userName: String,
    val tags: List<String>,
    val likes: Int,
    val downloads: Int,
    val comments: Int,
    val previewUrl: String,
    val middleImageUrl: String,
    val largeImageUrl: String,
)
