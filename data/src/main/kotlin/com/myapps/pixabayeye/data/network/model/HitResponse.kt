package com.myapps.pixabayeye.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HitResponse(
    @param:Json(name = "id") val imageId: Long,
    @param:Json(name = "user_id") val userId: Long,
    @param:Json(name = "user") val userName: String,
    @param:Json(name = "tags") val tags: String,
    @param:Json(name = "likes") val likes: Int,
    @param:Json(name = "downloads") val downloads: Int,
    @param:Json(name = "comments") val comments: Int,
    @param:Json(name = "previewURL") val previewUrl: String,
    @param:Json(name = "webformatURL") val middleImageUrl: String,
    @param:Json(name = "largeImageURL") val largeImageUrl: String,
)
