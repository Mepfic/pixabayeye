package com.myapps.pixabayeye.comose

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.myapps.pixabayeye.common.theme.PixabayTheme
import com.myapps.pixabayeye.search.state.SearchItemState
import com.myapps.pixabayeye.test.common.TestTags
import org.junit.Rule
import org.junit.Test

/**
 * Unit tests for SearchScreen composable in isolation.
 */
class SearchScreenComposableTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testEmptyState() {
        composeTestRule.setContent {
            PixabayTheme {
//            SearchPage ()
            }
        }

        composeTestRule.onNodeWithTag(TestTags.SEARCH_INPUT)
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag(TestTags.SEARCH_EMPTY)
            .assertIsDisplayed()
    }

    @Test
    fun testLoadingState() {
        composeTestRule.setContent {
            PixabayTheme {
//            SearchPage ()
            }
        }

        composeTestRule.onNodeWithTag(TestTags.SEARCH_LOADING)
            .assertIsDisplayed()
    }

    @Test
    fun testErrorState() {
        val errorMessage = "Network error"

        composeTestRule.setContent {
            PixabayTheme {
//            SearchPage ()
            }
        }

        composeTestRule.onNodeWithTag(TestTags.SEARCH_ERROR)
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag(TestTags.ERROR_MESSAGE)
            .assertTextContains(errorMessage)

        composeTestRule.onNodeWithTag(TestTags.RETRY_BUTTON)
            .assertIsDisplayed()
    }

    @Test
    fun testResultsDisplayed() {
        val testItems = listOf(
            SearchItemState(1, "image1.jpg", listOf("cat, animal"), "user1"),
            SearchItemState(2, "image2.jpg", listOf("dog, pet"), "user2"),
            SearchItemState(3, "image3.jpg", listOf("nature, tree"), "user3")
        )

        composeTestRule.setContent {
            PixabayTheme {
//            SearchPage ()
            }
        }

        composeTestRule.onNodeWithTag(TestTags.SEARCH_RESULTS_LIST)
            .assertIsDisplayed()

        // Verify all items are present
        composeTestRule.onAllNodesWithTag(TestTags.SEARCH_ITEM)
            .assertCountEquals(testItems.size)
    }

    @Test
    fun testSearchInputCallback() {
        var searchedQuery = ""

        composeTestRule.setContent {
            PixabayTheme {
//            SearchPage ()
            }
        }

        val testQuery = "flowers"
        composeTestRule.onNodeWithTag(TestTags.SEARCH_INPUT)
            .performTextInput(testQuery)

        assert(searchedQuery == testQuery)
    }

    @Test
    fun testItemClickCallback() {
        var clickedItemId = -1
        val testItems = listOf(
            SearchItemState(1, "image1.jpg", listOf("cat"), "user1")
        )

        composeTestRule.setContent {
            PixabayTheme {
//            SearchPage ()
            }
        }

        composeTestRule.onNodeWithTag("${TestTags.SEARCH_ITEM}_1")
            .performClick()

        assert(clickedItemId == 1)
    }

    @Test
    fun testLoadMoreIndicator() {
        val testItems = listOf(
            SearchItemState(1, "image1.jpg", listOf("cat"), "user1")
        )

        composeTestRule.setContent {
            PixabayTheme {
//            SearchPage ()
            }
        }

        composeTestRule.onNodeWithTag(TestTags.LOAD_MORE_INDICATOR)
            .assertIsDisplayed()
    }
}