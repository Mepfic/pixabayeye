package com.myapps.pixabayeye.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.myapps.pixabayeye.data.database.model.SearchEntity

@Dao
interface SearchDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(result: List<SearchEntity>)

    @Query("DELETE FROM search WHERE querySearch = :query")
    suspend fun clearSearch(query: String)

    @Transaction
    suspend fun refreshSearch(query: String, result: List<SearchEntity>) {
        clearSearch(query)
        insertAll(result)
    }
}
