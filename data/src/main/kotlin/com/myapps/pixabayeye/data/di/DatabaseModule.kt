package com.myapps.pixabayeye.data.di

import android.content.Context
import androidx.room.Room
import com.myapps.pixabayeye.data.database.AppDatabase
import com.myapps.pixabayeye.data.database.dao.ImagesDao
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
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideImagesDao(database: AppDatabase): ImagesDao = database.imagesDao()

    private const val DATABASE_NAME = "pixabayeye.db"
}
