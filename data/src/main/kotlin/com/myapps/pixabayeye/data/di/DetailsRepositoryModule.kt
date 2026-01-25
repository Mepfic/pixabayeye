package com.myapps.pixabayeye.data.di

import com.myapps.pixabayeye.data.datasource.DetailsRepositoryImpl
import com.myapps.pixabayeye.domain.DetailsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DetailsRepositoryModule {

    @Binds
    @Singleton
    fun bindDetailsRepository(detailsRepository: DetailsRepositoryImpl): DetailsRepository
}