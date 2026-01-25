package com.myapps.pixabayeye.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.myapps.pixabayeye.common.theme.PixabayTheme
import com.myapps.pixabayeye.details.ui.Details
import com.myapps.pixabayeye.search.ui.Search

/**
 * Creates a test NavHostController for Navigation3 testing
 */
fun createTestNavController(): TestNavHostController {
    return TestNavHostController(ApplicationProvider.getApplicationContext()).apply {
        navigatorProvider.addNavigator(ComposeNavigator())
    }
}

/**
 * Navigate to search screen in tests
 */
fun TestNavHostController.navigateToSearch() {
    navigate(Search)
}

/**
 * Navigate to details screen in tests
 */
fun TestNavHostController.navigateToDetails(imageId: Long) {
    navigate(Details(imageId))
}

/**
 * Sets content with theme wrapper for consistent testing
 */
fun ComposeContentTestRule.setContentWithTheme(
    content: @Composable () -> Unit
) {
    setContent {
        PixabayTheme {
            content()
        }
    }
}

/**
 * Assert current navigation route
 */
fun TestNavHostController.assertCurrentRoute(route: Any) {
    assert(currentBackStackEntry?.destination?.route == route::class.qualifiedName) {
        "Expected route ${route::class.simpleName}, but was ${currentBackStackEntry?.destination?.route}"
    }
}

/**
 * Wait for condition with timeout
 */
fun ComposeContentTestRule.waitUntilExists(
    matcher: SemanticsMatcher,
    timeoutMillis: Long = 3000L
) {
    waitUntil(timeoutMillis) {
        onAllNodes(matcher).fetchSemanticsNodes().isNotEmpty()
    }
}

/**
 * Wait for condition to be false
 */
fun ComposeContentTestRule.waitUntilDoesNotExist(
    matcher: SemanticsMatcher,
    timeoutMillis: Long = 3000L
) {
    waitUntil(timeoutMillis) {
        onAllNodes(matcher).fetchSemanticsNodes().isEmpty()
    }
}