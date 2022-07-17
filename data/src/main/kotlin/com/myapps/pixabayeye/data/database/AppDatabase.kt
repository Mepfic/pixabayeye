package com.myapps.pixabayeye.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.myapps.pixabayeye.data.database.dao.ImagesDao
import com.myapps.pixabayeye.data.database.model.HitEntity

@Database(
    entities = [
        HitEntity::class
    ],
    version = AppDatabase.VERSION
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun imagesDao(): ImagesDao

    companion object {
        const val VERSION = 1
    }
}