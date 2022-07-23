package com.myapps.pixabayeye.di

import androidx.paging.ExperimentalPagingApi
import com.myapps.pixabayeye.domain.DetailsRepository
import com.myapps.pixabayeye.domain.di.DetailsRepositoryModule
import com.myapps.pixabayeye.repository.StubDetailsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@ExperimentalPagingApi
@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DetailsRepositoryModule::class]
)
abstract class StubRepositoryModule {

    @Singleton
    @Binds
    abstract fun bindDetailsRepository(
        repository: StubDetailsRepository
    ): DetailsRepository
}
