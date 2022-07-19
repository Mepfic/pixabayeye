package com.myapps.pixabayeye.data.database.model

import androidx.room.Entity

@Entity(tableName = "hits", primaryKeys = ["imageId"])
data class HitEntity(
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