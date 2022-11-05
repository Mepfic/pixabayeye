package com.myapps.pixabayeye.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.myapps.pixabayeye.data.database.dao.ImagesDao
import com.myapps.pixabayeye.data.database.dao.SearchDao
import com.myapps.pixabayeye.data.database.model.HitEntity
import com.myapps.pixabayeye.data.database.model.SearchEntity

@Database(
    entities = [
        HitEntity::class,
        SearchEntity::class
    ],
    version = AppDatabase.VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun imagesDao(): ImagesDao

    abstract fun searchDao(): SearchDao

    companion object {
        const val VERSION = 1
    }
}
