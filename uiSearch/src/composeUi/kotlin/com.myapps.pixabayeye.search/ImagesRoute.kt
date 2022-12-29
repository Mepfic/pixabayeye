package com.myapps.pixabayeye.search

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.myapps.pixabayeye.search.screen.ImagesScreen
import com.myapps.pixabayeye.search.ui.SearchViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalLifecycleComposeApi
@Composable
fun ImagesRoute(viewModel: SearchViewModel, navigateToDetails: (Long) -> Unit) {
    val state by viewModel.dataFlow.collectAsStateWithLifecycle()

    ImagesScreen(state, navigateToDetails = { navigateToDetails(it) })
}