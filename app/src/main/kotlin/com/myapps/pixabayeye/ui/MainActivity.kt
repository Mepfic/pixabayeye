package com.myapps.pixabayeye.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.myapps.pixabayeye.common.theme.PixabayTheme
import com.myapps.pixabayeye.common.utils.setEdgeToEdgeConfig
import com.myapps.pixabayeye.ui.navigation.EntryProviderInstaller
import com.myapps.pixabayeye.ui.navigation.Navigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var entryProviderScopes: Set<@JvmSuppressWildcards EntryProviderInstaller>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setEdgeToEdgeConfig()
        setContent {
            PixabayTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    contentWindowInsets = WindowInsets.systemBars
                ) {
                    NavDisplay(
                        modifier = Modifier.padding(it),
                        backStack = navigator.backStack,
                        onBack = { navigator.goBack() },
                        entryProvider = entryProvider {
                            entryProviderScopes.forEach { builder ->
                                this.builder()
                            }
                        }
                    )
                }
            }
        }
    }
}
