package com.myapps.pixabayeye.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Constraints
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.myapps.pixabayeye.details.DetailsRoute
import com.myapps.pixabayeye.details.DetailsViewModel
import com.myapps.pixabayeye.search.ImagesRoute
import com.myapps.pixabayeye.search.ui.SearchViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalMaterialApi
@ExperimentalLifecycleComposeApi
@ExperimentalAnimationApi
@Composable
fun AppNavGraph(
    navController: NavHostController,
    constraints: Constraints,
    startDestination: String = Destination.Images.route
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = startDestination,
        builder = {
            addImages(navController, constraints)
            addDetails(constraints)

        }
    )
}

@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@ExperimentalLifecycleComposeApi
@ExperimentalAnimationApi
fun NavGraphBuilder.addImages(
    navController: NavController,
    constraints: Constraints, // TODO - implement transition animation
) {
    composable(
        route = Destination.Images.route,
    ){
        val viewModel: SearchViewModel = hiltViewModel()
        ImagesRoute(
            viewModel = viewModel,
            navigateToDetails = { id ->
                navController.navigate("${Destination.Details.route}/$id")
            }
        )
    }
}

@ExperimentalMaterialApi
@ExperimentalLifecycleComposeApi
@ExperimentalAnimationApi
fun NavGraphBuilder.addDetails(
    constraints: Constraints, // TODO - implement transition animation
) {
    composable(
        route = Destination.Details.route + "/{imageId}",
        arguments = Destination.Details.arguments,
    ){
        val viewModel: DetailsViewModel = hiltViewModel()
        DetailsRoute(viewModel)
    }
}
