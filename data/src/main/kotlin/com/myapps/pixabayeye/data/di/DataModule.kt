package com.myapps.pixabayeye.data.di

import com.myapps.pixabayeye.data.EndPoints
import com.myapps.pixabayeye.data.MainNetworkApi
import com.myapps.pixabayeye.data.NetworkService
import com.myapps.pixabayeye.data.cache.MemoryCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideNetworkApi(): MainNetworkApi {
        return NetworkService.build(EndPoints.PIXABAY_BASE_URL)
    }

    @Provides
    @Singleton
    fun provideMemoryCache(): MemoryCache {
        return MemoryCache()
    }
}