package com.myapps.pixabayeye.data.test

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.myapps.pixabayeye.data.database.AppDatabase

object SubDatabaseFactory {
    fun create(): AppDatabase = Room
        .inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        )
        .build()
}
