package com.myapps.pixabayeye.domain.model

data class HitModel(
    val imageId: Long,
    val userId: Long,
    val userName: String,
    val tags: String,
    val likes: Int,
    val downloads: Int,
    val comments: Int,
    val previewUrl: String,
    val middleImageUrl: String,
    val largeImageUrl: String,
)