package com.myapps.pixabayeye.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImagesResponse(
    @param:Json(name = "total") val total: Int,
    @param:Json(name = "totalHits") val totalHits: Int,
    @param:Json(name = "hits") val hits: List<HitResponse>,
)
