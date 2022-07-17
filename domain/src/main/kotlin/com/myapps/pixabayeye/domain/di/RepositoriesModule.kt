package com.myapps.pixabayeye.domain.di

import androidx.paging.ExperimentalPagingApi
import com.myapps.pixabayeye.domain.ImageRepository
import com.myapps.pixabayeye.domain.ImageRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@ExperimentalPagingApi
@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesModule {

    @Binds
    fun bindImagesRepository(
        imageRepository: ImageRepositoryImpl
    ): ImageRepository
}
