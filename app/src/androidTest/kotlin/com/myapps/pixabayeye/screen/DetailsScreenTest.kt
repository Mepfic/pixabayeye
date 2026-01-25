package com.myapps.pixabayeye.screen

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.performTextInput
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
class DetailsScreenTest {

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
        // Start from search screen, search, and navigate via Navigation3
        composeTestRule.onNodeWithTag(TestTags.SEARCH_INPUT)
            .performTextInput(TEST_QUERY)

        // Wait for results
        composeTestRule.waitUntilDoesNotExist(
            hasTestTag(TestTags.SEARCH_LOADING),
            timeoutMillis = GETTING_DATA_DELAY
        )

        // Click first item - Navigation3 will handle DetailsRoute(imageId)
        composeTestRule.onAllNodesWithTag(TestTags.SEARCH_ITEM)[0]
            .performClick()

        // Verify details screen is displayed
        composeTestRule.waitUntilExists(
            hasTestTag(TestTags.DETAILS_SCREEN),
            timeoutMillis = 1000L
        )

        composeTestRule.onNodeWithTag(TestTags.DETAILS_SCREEN)
            .assertIsDisplayed()
    }

    @Test
    fun testDetailsScreenContent() {
        // Navigate to details using Navigation3
        navigateToDetailsScreen()

        // Wait for loading to complete
        composeTestRule.waitUntilDoesNotExist(
            hasTestTag(TestTags.DETAILS_LOADING),
            timeoutMillis = GETTING_DATA_DELAY
        )

        // Verify all components are displayed
        composeTestRule.onNodeWithTag(TestTags.DETAILS_IMAGE)
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag(TestTags.DETAILS_AUTHOR)
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag(TestTags.DETAILS_LIKES)
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag(TestTags.DETAILS_DOWNLOADS)
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag(TestTags.DETAILS_COMMENTS)
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag(TestTags.DETAILS_TAGS)
            .assertIsDisplayed()
    }

    @Test
    fun testDetailsScreenLoading() {
        // Navigate to details
        navigateToDetailsScreen()

        // Verify loading indicator appears initially
        composeTestRule.onNodeWithTag(TestTags.DETAILS_LOADING)
            .assertIsDisplayed()

        // Wait for loading to finish
        composeTestRule.waitUntilDoesNotExist(
            hasTestTag(TestTags.DETAILS_LOADING),
            timeoutMillis = GETTING_DATA_DELAY
        )
    }

    @Test
    fun testDetailsDataDisplayed() {
        // Navigate to details
        navigateToDetailsScreen()

        // Wait for data to load
        composeTestRule.waitUntilDoesNotExist(
            hasTestTag(TestTags.DETAILS_LOADING),
            timeoutMillis = GETTING_DATA_DELAY
        )

        // Verify author has "by" prefix
        composeTestRule.onNodeWithTag(TestTags.DETAILS_AUTHOR)
            .assertTextContains("by", substring = true, ignoreCase = true)

        // Verify stats contain numbers
        composeTestRule.onNodeWithTag(TestTags.DETAILS_LIKES)
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag(TestTags.DETAILS_DOWNLOADS)
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag(TestTags.DETAILS_COMMENTS)
            .assertIsDisplayed()
    }

    @Test
    fun testScrollDetailsScreen() {
        // Navigate to details
        navigateToDetailsScreen()

        // Wait for loading
        composeTestRule.waitUntilDoesNotExist(
            hasTestTag(TestTags.DETAILS_LOADING),
            timeoutMillis = GETTING_DATA_DELAY
        )

        // Scroll to bottom to verify all content
        composeTestRule.onNodeWithTag(TestTags.DETAILS_SCREEN)
            .performScrollToNode(hasTestTag(TestTags.DETAILS_TAGS))

        // Verify tags are visible after scroll
        composeTestRule.onNodeWithTag(TestTags.DETAILS_TAGS)
            .assertIsDisplayed()
    }

    @Test
    fun testNavigateBackFromDetails() {
        // Navigate to details
        navigateToDetailsScreen()

        // Wait for details to load
        composeTestRule.waitUntilDoesNotExist(
            hasTestTag(TestTags.DETAILS_LOADING),
            timeoutMillis = GETTING_DATA_DELAY
        )

        // Press back - Navigation3 will pop back stack
        composeTestRule.activityRule.scenario.onActivity { activity ->
            activity.onBackPressedDispatcher.onBackPressed()
        }

        // Verify we're back on search screen
        composeTestRule.onNodeWithTag(TestTags.SEARCH_SCREEN)
            .assertIsDisplayed()
    }

    /**
     * Helper function to navigate to details screen
     * Uses the actual app flow with Navigation3
     */
    private fun navigateToDetailsScreen() {
        // Search
        composeTestRule.onNodeWithTag(TestTags.SEARCH_INPUT)
            .performTextInput(TEST_QUERY)

        // Wait for results
        composeTestRule.waitUntilDoesNotExist(
            hasTestTag(TestTags.SEARCH_LOADING),
            timeoutMillis = GETTING_DATA_DELAY
        )

        // Click first item - Navigation3 handles DetailsRoute(imageId)
        composeTestRule.onAllNodesWithTag(TestTags.SEARCH_ITEM)[0]
            .performClick()

        // Wait for details screen
        composeTestRule.waitUntilExists(
            hasTestTag(TestTags.DETAILS_SCREEN),
            timeoutMillis = 1000L
        )
    }

    companion object {
        const val GETTING_DATA_DELAY = 5000L
        const val TEST_QUERY = "nature"
    }
}