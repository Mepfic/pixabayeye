package com.myapps.pixabayeye.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImagesResponse(
    @Json(name = "total") val total: Int,
    @Json(name = "totalHits") val totalHits: Int,
    @Json(name = "hits") val hits: List<HitResponse>
)

@JsonClass(generateAdapter = true)
data class HitResponse(
    @Json(name = "id") val imageId: Long,
    @Json(name = "user_id") val userId: Long,
    @Json(name = "user") val userName: String,
    @Json(name = "tags") val tags: String,
    @Json(name = "likes") val likes: Int,
    @Json(name = "downloads") val downloads: Int,
    @Json(name = "previewURL") val previewUrl: String,
    @Json(name = "webformatURL") val middleImageUrl: String,
    @Json(name = "largeImageURL") val largeImageUrl: String,
)
