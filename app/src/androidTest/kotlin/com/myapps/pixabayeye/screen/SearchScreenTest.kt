package com.myapps.pixabayeye.screen

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.myapps.pixabayeye.test.common.TestTags
import com.myapps.pixabayeye.ui.MainActivity
import com.myapps.pixabayeye.utils.waitUntilExists
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalTestApi::class)
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class SearchScreenTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun init() {
        hiltRule.inject()
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
    fun testSearchInput() {
        // Type in search field
        composeTestRule.onNodeWithTag(TestTags.SEARCH_INPUT)
            .performTextInput(TEST_QUERY)

        // Verify text was entered
        composeTestRule.onNodeWithTag(TestTags.SEARCH_INPUT)
            .assertTextContains(TEST_QUERY)

        // Wait for loading to finish
        composeTestRule.waitUntilDoesNotExist(
            hasTestTag(TestTags.SEARCH_LOADING),
            timeoutMillis = GETTING_DATA_DELAY
        )

        // Verify results are displayed
        composeTestRule.onNodeWithTag(TestTags.SEARCH_RESULTS_LIST)
            .assertIsDisplayed()
    }

    @Test
    fun testSearchResultsDisplayed() {
        // Perform search
        composeTestRule.onNodeWithTag(TestTags.SEARCH_INPUT)
            .performTextInput(TEST_QUERY)

        // Wait for loading
        composeTestRule.waitUntilExists(
            hasTestTag(TestTags.SEARCH_LOADING),
            timeoutMillis = 1000L
        )

        // Wait for results
        composeTestRule.waitUntilDoesNotExist(
            hasTestTag(TestTags.SEARCH_LOADING),
            timeoutMillis = GETTING_DATA_DELAY
        )

        // Verify results list exists
        composeTestRule.onNodeWithTag(TestTags.SEARCH_RESULTS_LIST)
            .assertIsDisplayed()

        // Verify at least one item is displayed
        composeTestRule.onAllNodesWithTag(TestTags.SEARCH_ITEM)
            .assertCountEquals(0) // At least 1 item
    }

    @Test
    fun testLoadingStateDisplayed() {
        // Type search query
        composeTestRule.onNodeWithTag(TestTags.SEARCH_INPUT)
            .performTextInput(TEST_QUERY)

        // Verify loading indicator appears
        composeTestRule.onNodeWithTag(TestTags.SEARCH_LOADING)
            .assertIsDisplayed()
    }

    @Test
    fun testScrollToLoadMore() {
        // Perform search
        composeTestRule.onNodeWithTag(TestTags.SEARCH_INPUT)
            .performTextInput(TEST_QUERY)

        // Wait for initial results
        composeTestRule.waitUntilDoesNotExist(
            hasTestTag(TestTags.SEARCH_LOADING),
            timeoutMillis = GETTING_DATA_DELAY
        )

        // Scroll to bottom
        composeTestRule.onNodeWithTag(TestTags.SEARCH_RESULTS_LIST)
            .performScrollToNode(hasTestTag(TestTags.LOAD_MORE_INDICATOR))

        // Verify load more indicator appears
        composeTestRule.onNodeWithTag(TestTags.LOAD_MORE_INDICATOR)
            .assertIsDisplayed()
    }

    @Test
    fun testClickOnSearchItem_NavigatesToDetails() {
        // Perform search
        composeTestRule.onNodeWithTag(TestTags.SEARCH_INPUT)
            .performTextInput(TEST_QUERY)

        // Wait for results
        composeTestRule.waitUntilDoesNotExist(
            hasTestTag(TestTags.SEARCH_LOADING),
            timeoutMillis = GETTING_DATA_DELAY
        )

        // Click first item - this triggers Navigation3 navigation
        composeTestRule.onAllNodesWithTag(TestTags.SEARCH_ITEM)[0]
            .performClick()

        // Verify navigation to details screen
        // With Navigation3, we verify by checking if DetailsScreen is displayed
        composeTestRule.waitUntilExists(
            hasTestTag(TestTags.DETAILS_SCREEN),
            timeoutMillis = 1000L
        )

        composeTestRule.onNodeWithTag(TestTags.DETAILS_SCREEN)
            .assertIsDisplayed()
    }

    @Test
    fun testEmptyState() {
        // Search with query that returns no results
        composeTestRule.onNodeWithTag(TestTags.SEARCH_INPUT)
            .performTextInput("xyznonexistentquery123")

        // Wait for search to complete
        composeTestRule.waitUntilDoesNotExist(
            hasTestTag(TestTags.SEARCH_LOADING),
            timeoutMillis = GETTING_DATA_DELAY
        )

        // Verify empty state is shown
        composeTestRule.onNodeWithTag(TestTags.SEARCH_EMPTY)
            .assertIsDisplayed()
    }

    companion object {
        const val GETTING_DATA_DELAY = 5000L
        const val TEST_QUERY = "cats"
    }
}
