package com.myapps.pixabayeye.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Destination(val route: String, val arguments: List<NamedNavArgument>){

    object Images: Destination(
        route = "images",
        arguments = emptyList()
    )

    object Details: Destination(
        route = "details",
        arguments = listOf(navArgument("imageId") {
            type = NavType.LongType
        })
    )
}