package com.myapps.pixabayeye.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.myapps.pixabayeye.data.database.model.HitEntity

@Dao
interface ImagesDao {
    @Query("""
        SELECT * FROM hits 
        INNER JOIN search ON hits.imageId = search.imageId 
        WHERE search.querySearch = :query
        ORDER BY hits.imageId DESC
    """)
    fun getImagesByQuery(query: String): PagingSource<Int, HitEntity>

    @Query("""
        SELECT COUNT(*) FROM hits 
        INNER JOIN search ON hits.imageId = search.imageId 
        WHERE search.querySearch = :query
    """)
    suspend fun getCachedCount(query: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(hits: List<HitEntity>)

    @Query("DELETE FROM hits WHERE imageId = :imageId")
    suspend fun deleteById(imageId: Long)

    @Query("SELECT * FROM hits WHERE imageId = :id")
    suspend fun getHitById(id: Long): HitEntity

    @Query("""
        DELETE FROM hits 
        WHERE imageId NOT IN (SELECT DISTINCT imageId FROM search)
    """)
    suspend fun cleanOrphans()
}
