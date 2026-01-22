package com.myapps.pixabayeye.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.myapps.pixabayeye.data.database.model.SearchEntity
import com.myapps.pixabayeye.data.database.model.SearchQueryEntity

@Dao
interface SearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuery(query: SearchQueryEntity)

    @Query("SELECT * FROM search_queries WHERE queryText = :query")
    suspend fun getQuery(query: String): SearchQueryEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(result: List<SearchEntity>)

    @Query("DELETE FROM search WHERE querySearch = :query")
    suspend fun clearSearch(query: String)

    @Query("DELETE FROM search_queries WHERE timestamp < :cutoffTimestamp")
    suspend fun clearQueriesOlderThan(cutoffTimestamp: Long)

    @Transaction
    suspend fun refreshSearch(query: String, result: List<SearchEntity>, page: Int) {
        if (page == 1) {
            clearSearch(query)
        }
        insertAll(result)
        insertQuery(
            SearchQueryEntity(
                queryText = query,
                timestamp = System.currentTimeMillis() / 1000,
                lastFetchedPage = page
            )
        )
    }
}
