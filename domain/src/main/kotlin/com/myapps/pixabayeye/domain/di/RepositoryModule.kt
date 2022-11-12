package com.myapps.pixabayeye.domain.di

import androidx.paging.ExperimentalPagingApi
import com.myapps.pixabayeye.domain.MainRepositoryImpl
import com.myapps.pixabayeye.domain.MainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@ExperimentalPagingApi
@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindMainRepository(repository: MainRepositoryImpl): MainRepository
}