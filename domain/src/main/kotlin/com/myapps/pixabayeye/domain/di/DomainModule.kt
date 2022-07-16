package com.myapps.pixabayeye.domain.di

import com.myapps.pixabayeye.data.MainNetworkApi
import com.myapps.pixabayeye.data.cache.MemoryCache
import com.myapps.pixabayeye.domain.DetailsUseCase
import com.myapps.pixabayeye.domain.ImageRepository
import com.myapps.pixabayeye.domain.ImageRepositoryImpl
import com.myapps.pixabayeye.domain.ImagesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideImageRepository(
        mainNetworkApi: MainNetworkApi,
        memoryCache: MemoryCache
    ): ImageRepository =
        ImageRepositoryImpl(mainNetworkApi, memoryCache)

    @Provides
    @Singleton
    fun provideImageUseCase(
        imageRepository: ImageRepository
    ): ImagesUseCase = ImagesUseCase(imageRepository)

    @Provides
    @Singleton
    fun provideDetailsUseCase(
        imageRepository: ImageRepository
    ): DetailsUseCase = DetailsUseCase(imageRepository)

}