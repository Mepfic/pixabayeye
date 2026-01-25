package com.myapps.pixabayeye.di

import androidx.navigation3.runtime.NavKey
import com.myapps.pixabayeye.search.ui.Search
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object AppModule {

    @Provides
    @ActivityRetainedScoped
    fun provideStartRoute(): NavKey = Search

}

