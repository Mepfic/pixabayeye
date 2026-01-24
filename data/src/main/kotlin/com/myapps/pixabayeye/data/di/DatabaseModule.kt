package com.myapps.pixabayeye.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.myapps.pixabayeye.data.database.AppDatabase
import com.myapps.pixabayeye.data.database.dao.ImagesDao
import com.myapps.pixabayeye.data.database.dao.SearchDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        )
            .addMigrations(MIGRATION_1_2)
            .build()

    @Provides
    fun provideSearchDao(database: AppDatabase): SearchDao = database.searchDao()

    @Provides
    fun provideImagesDao(database: AppDatabase): ImagesDao = database.imagesDao()

    private val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("""
                CREATE TABLE IF NOT EXISTS search_queries (
                    queryText TEXT PRIMARY KEY NOT NULL,
                    timestamp INTEGER NOT NULL,
                    lastFetchedPage INTEGER NOT NULL DEFAULT 0
                )
            """)
        }
    }

    private const val DATABASE_NAME = "pixabayeye.db"
}
