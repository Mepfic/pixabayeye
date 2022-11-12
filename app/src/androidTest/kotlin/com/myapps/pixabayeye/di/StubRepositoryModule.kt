package com.myapps.pixabayeye.di

import androidx.paging.ExperimentalPagingApi
import com.myapps.pixabayeye.domain.MainRepository
import com.myapps.pixabayeye.domain.di.RepositoryModule
//import com.myapps.pixabayeye.domain.MainRepository
//import com.myapps.pixabayeye.domain.di.RepositoryModule
import com.myapps.pixabayeye.repository.StubRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@ExperimentalPagingApi
@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
abstract class StubRepositoryModule {

    @Singleton
    @Binds
    abstract fun bindRepository(
        repository: StubRepository
    ): MainRepository
}
