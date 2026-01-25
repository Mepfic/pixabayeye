package com.myapps.pixabayeye.details.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavKey
import com.myapps.pixabayeye.common.utils.isLandscape
import kotlinx.serialization.Serializable

/**
 * Destination for the Details Page
 */
@Serializable
data class Details(val imageId: Long) : NavKey

@Composable
fun DetailsRoute(
    imageId: Long,
    viewModel: DetailsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.dataFlow.collectAsStateWithLifecycle()

    LaunchedEffect(imageId) {
        viewModel.load(imageId)
    }

    if (isLandscape()) DetailsScreenLandscape(uiState)
    else DetailsScreen(uiState)

}