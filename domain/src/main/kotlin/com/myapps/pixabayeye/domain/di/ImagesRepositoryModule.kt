package com.myapps.pixabayeye.domain.di

import androidx.paging.ExperimentalPagingApi
import com.myapps.pixabayeye.domain.ImagesRepository
import com.myapps.pixabayeye.domain.ImagesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@ExperimentalPagingApi
@Module
@InstallIn(SingletonComponent::class)
interface ImagesRepositoryModule {

    @Binds
    fun bindImagesRepository(
        imagesRepository: ImagesRepositoryImpl
    ): ImagesRepository
}
