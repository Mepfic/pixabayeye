package com.myapps.pixabayeye.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.myapps.pixabayeye.data.database.model.HitEntity

@Dao
interface ImagesDao {
    @Query(
        "SELECT * FROM hits INNER JOIN search ON hits.imageId = search.imageId " +
            "WHERE search.querySearch = :query"
    )
    fun getImagesByQuery(query: String): PagingSource<Int, HitEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(hits: List<HitEntity>)

    @Query("DELETE FROM hits WHERE imageId = :imageId")
    suspend fun deleteById(imageId: Long)

    @Query("SELECT * FROM hits WHERE imageId = :id")
    suspend fun getHitById(id: Long): HitEntity
}
