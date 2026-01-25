package com.myapps.pixabayeye.search.ui

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.NavKey
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.serialization.Serializable

/**
 * Destination for the Search Page
 */
@Serializable
object Search : NavKey

@Composable
fun SearchRoute(
    viewModel: SearchViewModel = hiltViewModel(),
    navigateToDetails: (Long) -> Unit,
) {
    val items = viewModel.dataFlow.collectAsLazyPagingItems()

    SearchPage(
        items = items,
        onSearchClick = { viewModel.getImages(it) },
        navigateToDetails = navigateToDetails
    )
}
