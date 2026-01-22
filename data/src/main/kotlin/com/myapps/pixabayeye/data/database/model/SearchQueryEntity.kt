package com.myapps.pixabayeye.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_queries")
data class SearchQueryEntity(
    @PrimaryKey val queryText: String,
    val timestamp: Long, // Unix timestamp in seconds
    val lastFetchedPage: Int = 0,
)