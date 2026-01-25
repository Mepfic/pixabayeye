package com.myapps.pixabayeye.uisearch

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.myapps.pixabayeye.common.theme.PixabayTheme
import com.myapps.pixabayeye.search.state.SearchItemState
import com.myapps.pixabayeye.search.ui.SearchPage
import com.myapps.pixabayeye.test.common.TestTags
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test

/**
 * Unit tests for SearchPage composable in isolation.
 * These tests don't require Hilt or MainActivity.
 *
 * Note: Testing LazyPagingItems in unit tests is complex.
 * These tests demonstrate the structure, but integration tests
 * with real data flow are more reliable for pagination testing.
 */
class SearchScreenUnitTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testSearchScreenDisplayed() {
        val emptyPagingData = PagingData.empty<SearchItemState>()

        composeTestRule.setContent {
            PixabayTheme {
                val items = flowOf(emptyPagingData).collectAsLazyPagingItems()
                SearchPage(
                    items = items,
                    onSearchClick = {},
                    navigateToDetails = {}
                )
            }
        }

        // Verify search screen is displayed
        composeTestRule.onNodeWithTag(TestTags.SEARCH_SCREEN)
            .assertIsDisplayed()

        // Verify SearchBar is displayed
        composeTestRule.onNodeWithTag(TestTags.SEARCH_INPUT)
            .assertIsDisplayed()
    }

    @Test
    fun testSearchBarInteraction() {
        val emptyPagingData = PagingData.empty<SearchItemState>()

        composeTestRule.setContent {
            PixabayTheme {
                val items = flowOf(emptyPagingData).collectAsLazyPagingItems()
                SearchPage(
                    items = items,
                    onSearchClick = {},
                    navigateToDetails = {}
                )
            }
        }

        // Click SearchBar to expand
        composeTestRule.onNodeWithTag(TestTags.SEARCH_INPUT)
            .performClick()

        // Type in search field
        composeTestRule.onNodeWithText("", substring = true)
            .performTextInput("cats")

        // Verify text is entered
        composeTestRule.onNodeWithText("cats")
            .assertIsDisplayed()
    }

    @Test
    fun testSearchBarClearButton() {
        val emptyPagingData = PagingData.empty<SearchItemState>()

        composeTestRule.setContent {
            PixabayTheme {
                val items = flowOf(emptyPagingData).collectAsLazyPagingItems()
                SearchPage(
                    items = items,
                    onSearchClick = {},
                    navigateToDetails = {}
                )
            }
        }

        // Click and type
        composeTestRule.onNodeWithTag(TestTags.SEARCH_INPUT)
            .performClick()

        composeTestRule.onNodeWithText("", substring = true)
            .performTextInput("test query")

        // Clear button should appear when text is not empty
        // The clear button is an IconButton with ic_delete icon
        // It should be clickable
        composeTestRule.onAllNodes(hasClickAction())
            .assertCountEquals(2) // SearchBar + clear button
    }

    @Test
    fun testLoadingIndicatorDisplayed() {
        // Create PagingData with loading state
        val loadingPagingData = PagingData.empty<SearchItemState>(
            sourceLoadStates = LoadStates(
                refresh = LoadState.Loading,
                append = LoadState.NotLoading(false),
                prepend = LoadState.NotLoading(false)
            )
        )

        composeTestRule.setContent {
            PixabayTheme {
                val items = flowOf(loadingPagingData).collectAsLazyPagingItems()
                SearchPage(
                    items = items,
                    onSearchClick = {},
                    navigateToDetails = {}
                )
            }
        }

        // Wait for composition
        composeTestRule.waitForIdle()

        // Loading indicator should be displayed
        composeTestRule.onNodeWithTag(TestTags.SEARCH_LOADING)
            .assertIsDisplayed()
    }

    @Test
    fun testErrorMessageDisplayed() {
        // Create PagingData with error state
        val errorPagingData = PagingData.empty<SearchItemState>(
            sourceLoadStates = LoadStates(
                refresh = LoadState.Error(Exception("Network error")),
                append = LoadState.NotLoading(false),
                prepend = LoadState.NotLoading(false)
            )
        )

        composeTestRule.setContent {
            PixabayTheme {
                val items = flowOf(errorPagingData).collectAsLazyPagingItems()
                SearchPage(
                    items = items,
                    onSearchClick = {},
                    navigateToDetails = {}
                )
            }
        }

        // Wait for composition
        composeTestRule.waitForIdle()

        // Error message should be displayed
        composeTestRule.onNodeWithTag(TestTags.ERROR_MESSAGE)
            .assertIsDisplayed()
    }

    @Test
    fun testSearchCallbackTriggered() {
        var searchedQuery = ""
        val emptyPagingData = PagingData.empty<SearchItemState>()

        composeTestRule.setContent {
            PixabayTheme {
                val items = flowOf(emptyPagingData).collectAsLazyPagingItems()
                SearchPage(
                    items = items,
                    onSearchClick = { query -> searchedQuery = query },
                    navigateToDetails = {}
                )
            }
        }

        // Click SearchBar
        composeTestRule.onNodeWithTag(TestTags.SEARCH_INPUT)
            .performClick()

        // Type query
        val testQuery = "flowers"
        composeTestRule.onNodeWithText("", substring = true)
            .performTextInput(testQuery)

        // Submit search
        composeTestRule.onNodeWithText(testQuery)
            .performImeAction()

        // Verify callback was called
        assert(searchedQuery == testQuery)
    }

}