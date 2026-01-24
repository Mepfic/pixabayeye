package com.myapps.pixabayeye.data.di

import com.myapps.pixabayeye.data.datasource.ImagesRepositoryImpl
import com.myapps.pixabayeye.domain.ImagesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ImagesRepositoryModule {

    @Binds
    @Singleton
    fun bindImagesRepository(imagesRepository: ImagesRepositoryImpl): ImagesRepository
}