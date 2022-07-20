package com.myapps.pixabayeye.data.database.model

import androidx.room.Entity

@Entity(tableName = "search", primaryKeys = ["imageId", "querySearch"])
data class SearchEntity(
    val imageId: Long,
    val querySearch: String,
)