package com.myapps.pixabayeye.domain.di

import androidx.paging.ExperimentalPagingApi
import com.myapps.pixabayeye.domain.DetailsRepository
import com.myapps.pixabayeye.domain.DetailsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@ExperimentalPagingApi
@Module
@InstallIn(SingletonComponent::class)
interface DetailsRepositoryModule {

    @Binds
    fun bindDetailsRepository(
        detailsRepository: DetailsRepositoryImpl
    ): DetailsRepository
}
