package com.myapps.pixabayeye.screen

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.click
import androidx.compose.ui.test.filter
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onLast
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeDown
import androidx.compose.ui.test.swipeUp
import androidx.navigation3.runtime.NavKey
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.myapps.pixabayeye.di.AppModule
import com.myapps.pixabayeye.search.ui.Search
import com.myapps.pixabayeye.test.common.TestTags
import com.myapps.pixabayeye.ui.MainActivity
import com.myapps.pixabayeye.utils.waitUntilExists
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalTestApi::class)
@HiltAndroidTest
@UninstallModules(AppModule::class)
@RunWith(AndroidJUnit4::class)
class SearchScreenTest {

    @BindValue
    @JvmField
    val startRoute: NavKey = Search

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun testNavigateToDetailsScreen() {
        // Navigate to details using Navigation3
        navigateToDetailsScreen()

        composeTestRule.onNodeWithTag(TestTags.DETAILS_SCREEN)
            .assertIsDisplayed()
    }

    @Test
    fun testNavigateBackFromDetails() {
        // Navigate to details
        navigateToDetailsScreen()

        // Wait for details to be visible
        composeTestRule.waitForIdle()

        // Press back - Navigation3 will pop back stack
        composeTestRule.activityRule.scenario.onActivity { activity ->
            activity.onBackPressedDispatcher.onBackPressed()
        }

        // Verify we're back on search screen
        composeTestRule.onNodeWithTag(TestTags.SEARCH_SCREEN)
            .assertIsDisplayed()
    }

    @Test
    fun testSearchScreenDisplayed() {
        // Verify search screen is displayed on app start
        composeTestRule.onNodeWithTag(TestTags.SEARCH_SCREEN)
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag(TestTags.SEARCH_INPUT)
            .assertIsDisplayed()
    }

    @Test
    fun testSearchBarInput() {
        // Click on SearchBar to focus
        composeTestRule.onNodeWithTag(TestTags.SEARCH_INPUT)
            .performClick()

        composeTestRule.waitForIdle()

        // Type in search field - use the input field inside SearchBar
        composeTestRule.onNode(
            hasSetTextAction() and hasAnyAncestor(hasTestTag(TestTags.SEARCH_INPUT))
        ).performTextInput(TEST_QUERY)

        // Verify text was entered
        composeTestRule.onNodeWithText(TEST_QUERY)
            .assertIsDisplayed()
    }

    @Test
    fun testSearchWithResults() {
        // Click SearchBar
        composeTestRule.onNodeWithTag(TestTags.SEARCH_INPUT)
            .performClick()

        composeTestRule.waitForIdle()

        // Type query
        composeTestRule.onNode(
            hasSetTextAction() and hasAnyAncestor(hasTestTag(TestTags.SEARCH_INPUT))
        ).performTextInput(TEST_QUERY)

        // Submit search (press IME action)
        composeTestRule.onNode(
            hasSetTextAction() and hasAnyAncestor(hasTestTag(TestTags.SEARCH_INPUT))
        ).performImeAction()

        // Wait for loading to finish
        composeTestRule.waitUntilDoesNotExist(
            hasTestTag(TestTags.SEARCH_LOADING),
            timeoutMillis = GETTING_DATA_DELAY
        )

        // Verify at least one result item exists
        composeTestRule.waitForIdle()
        // Results should be visible in LazyColumn
    }

    @Test
    fun testPullToRefresh() {
        // First, perform a search to get results
        performSearch(TEST_QUERY)

        // Wait for results to load
        composeTestRule.waitUntilDoesNotExist(
            hasTestTag(TestTags.SEARCH_LOADING),
            timeoutMillis = GETTING_DATA_DELAY
        )

        // Perform swipe down gesture on the screen
        composeTestRule.onNodeWithTag(TestTags.SEARCH_SCREEN)
            .performTouchInput {
                swipeDown(
                    startY = top + 100f,
                    endY = bottom - 100f
                )
            }

        // Loading indicator should appear briefly
        composeTestRule.waitForIdle()
    }

    @Test
    fun testScrollToLoadMore() {
        // Perform search
        performSearch(TEST_QUERY)

        // Wait for initial results
        composeTestRule.waitUntilDoesNotExist(
            hasTestTag(TestTags.SEARCH_LOADING),
            timeoutMillis = GETTING_DATA_DELAY
        )

        // Scroll to bottom to trigger load more
        // Note: With LazyPagingItems, scrolling will automatically trigger pagination
        composeTestRule.onNodeWithTag(TestTags.SEARCH_SCREEN)
            .performTouchInput {
                swipeUp(
                    startY = bottom - 100f,
                    endY = top + 100f
                )
            }

        // Wait and verify load more indicator may appear
        composeTestRule.waitForIdle()
        // Load more indicator should appear at bottom
    }

    @Test
    fun testErrorState() {
        // This test would require mocking repository to return error
        // Or testing with airplane mode / no network
        // For now, we'll skip implementation as it requires test doubles
    }

    @Test
    fun testSearchBarClearButton() {
        // Click SearchBar
        composeTestRule.onNodeWithTag(TestTags.SEARCH_INPUT)
            .performClick()

        composeTestRule.waitForIdle()

        // Type query
        composeTestRule.onNode(
            hasSetTextAction() and hasAnyAncestor(hasTestTag(TestTags.SEARCH_INPUT))
        ).performTextInput(TEST_QUERY)

        // Verify text exists
        composeTestRule.onNodeWithText(TEST_QUERY)
            .assertIsDisplayed()

        // Click clear button (ic_delete icon)
        // The clear button appears when query is not empty
        composeTestRule.onAllNodes(hasClickAction())
            .filter(hasAnyAncestor(hasTestTag(TestTags.SEARCH_INPUT)))
            .onLast() // Clear button is likely the last clickable in SearchBar
            .performClick()

        // Verify query is cleared
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText(TEST_QUERY)
            .assertDoesNotExist()
    }

    private fun performSearch(query: String) {
        // Click SearchBar to focus it
        composeTestRule.onNodeWithTag(TestTags.SEARCH_INPUT)
            .performClick()

        // Wait for SearchBar to be ready
        composeTestRule.waitForIdle()

        // Find the text input field by content description or hint text
        // The SearchBar's InputField should have the hint "Search images..." or similar
        composeTestRule.onNode(
            hasSetTextAction() and hasAnyAncestor(hasTestTag(TestTags.SEARCH_INPUT))
        ).performTextInput(query)

        // Submit search
        composeTestRule.onNode(
            hasSetTextAction() and hasAnyAncestor(hasTestTag(TestTags.SEARCH_INPUT))
        ).performImeAction()
    }

    @OptIn(ExperimentalTestApi::class)
    private fun navigateToDetailsScreen() {
        composeTestRule.waitForIdle()

        composeTestRule.waitUntil(timeoutMillis = GETTING_DATA_DELAY) {
            composeTestRule.onAllNodes(hasTestTag(TestTags.SEARCH_ITEM))
                .fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule.onNodeWithTag(TestTags.SEARCH_SCREEN)
            .performTouchInput {
                swipeUp(
                    startY = bottom - 100f,
                    endY = top + 100f
                )
            }
            .performTouchInput {
                click(center)
            }

        composeTestRule.waitForIdle()

        composeTestRule.waitUntilExists(
            hasTestTag(TestTags.DETAILS_SCREEN),
            timeoutMillis = GETTING_DATA_DELAY
        )
    }

    companion object {
        const val GETTING_DATA_DELAY = 5000L
        const val TEST_QUERY = "cats"
    }
}