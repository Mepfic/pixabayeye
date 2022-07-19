package com.myapps.pixabayeye.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HitResponse(
    @Json(name = "id") val imageId: Long,
    @Json(name = "user_id") val userId: Long,
    @Json(name = "user") val userName: String,
    @Json(name = "tags") val tags: String,
    @Json(name = "likes") val likes: Int,
    @Json(name = "downloads") val downloads: Int,
    @Json(name = "comments") val comments: Int,
    @Json(name = "previewURL") val previewUrl: String,
    @Json(name = "webformatURL") val middleImageUrl: String,
    @Json(name = "largeImageURL") val largeImageUrl: String,
)