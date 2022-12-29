package com.myapps.pixabayeye.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material.ExperimentalMaterialApi
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.myapps.pixabayeye.details.theme.PixabayTheme
import com.myapps.pixabayeye.ui.navigation.AppNavGraph
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@ExperimentalLifecycleComposeApi
@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PixabayTheme {
                BoxWithConstraints {
                    val navController = rememberAnimatedNavController()
                    AppNavGraph(navController, constraints)
                }
            }
        }
    }
}
