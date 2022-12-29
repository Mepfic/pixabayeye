package com.myapps.pixabayeye.details

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.myapps.pixabayeye.details.screen.DetailsScreen

@ExperimentalMaterialApi
@ExperimentalLifecycleComposeApi
@Composable
fun DetailsRoute(viewModel: DetailsViewModel) {
    val state by viewModel.dataFlow.collectAsStateWithLifecycle()

    DetailsScreen(state = state)
}