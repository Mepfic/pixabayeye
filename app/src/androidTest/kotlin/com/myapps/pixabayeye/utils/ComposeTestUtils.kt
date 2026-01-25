@file:OptIn(ExperimentalCoilApi::class)

package com.myapps.pixabayeye.utils

import android.graphics.drawable.ColorDrawable
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.test.core.app.ApplicationProvider
import coil.Coil
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.test.FakeImageLoaderEngine


fun initFakeImageLoader() {
    val fakeEngine = FakeImageLoaderEngine.Builder()
        .default(ColorDrawable(android.graphics.Color.RED))
        .build()

    val fakeLoader = ImageLoader.Builder(ApplicationProvider.getApplicationContext())
        .components { add(fakeEngine) }
        .build()

    // Install fake loader
    Coil.setImageLoader(fakeLoader)
}

/**
 * Wait for condition with timeout
 */
fun ComposeContentTestRule.waitUntilExists(
    matcher: SemanticsMatcher,
    timeoutMillis: Long = 3000L,
) {
    waitUntil(timeoutMillis) {
        onAllNodes(matcher).fetchSemanticsNodes().isNotEmpty()
    }
}