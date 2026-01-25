package com.myapps.pixabayeye.di

import com.myapps.pixabayeye.details.ui.Details
import com.myapps.pixabayeye.details.ui.DetailsRoute
import com.myapps.pixabayeye.search.ui.Search
import com.myapps.pixabayeye.search.ui.SearchRoute
import com.myapps.pixabayeye.ui.navigation.EntryProviderInstaller
import com.myapps.pixabayeye.ui.navigation.Navigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(ActivityRetainedComponent::class)
object FeatureModule {

    @IntoSet
    @Provides
    fun provideEntryProviderInstaller(navigator: Navigator): EntryProviderInstaller =
        {
            entry<Search> {
                SearchRoute(
                    navigateToDetails = { id -> navigator.navigateTo(Details(id)) }
                )
            }

            entry<Details> {
                DetailsRoute(imageId = it.imageId)
            }
        }
}