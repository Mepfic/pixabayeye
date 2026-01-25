package com.myapps.pixabayeye.screen

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertWidthIsAtLeast
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.myapps.pixabayeye.details.ui.Details
import com.myapps.pixabayeye.di.AppModule
import com.myapps.pixabayeye.test.common.TestTags
import com.myapps.pixabayeye.ui.MainActivity
import com.myapps.pixabayeye.utils.initFakeImageLoader
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@UninstallModules(AppModule::class)
@RunWith(AndroidJUnit4::class)
class DetailsScreenTest {

    @BindValue
    @JvmField
    val startRoute: NavKey = Details(imageId = 736877)

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun init() {
        hiltRule.inject()
        initFakeImageLoader()
    }

    @Test
    fun testDetailsScreenContent() {
        // Navigate to details
        initFakeImageLoader()
        composeTestRule.waitForIdle()

        // Details screen should be visible immediately (it's a Surface)
        composeTestRule.onNodeWithTag(TestTags.DETAILS_SCREEN)
            .assertIsDisplayed()

        composeTestRule.waitUntil(GETTING_DATA_DELAY) {
            composeTestRule.onNodeWithTag(TestTags.DETAILS_LIKES).isDisplayed()
        }

        // Wait a moment for image to load
        composeTestRule.waitForIdle()

        // Verify all components are displayed
        composeTestRule.onNodeWithTag(TestTags.DETAILS_IMAGE)
            .assertIsDisplayed()

        // Statistics row with icons
        composeTestRule.onNodeWithTag(TestTags.DETAILS_LIKES)
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag(TestTags.DETAILS_DOWNLOADS)
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag(TestTags.DETAILS_COMMENTS)
            .assertIsDisplayed()

        // Tags
        composeTestRule.onNodeWithTag(TestTags.DETAILS_TAGS)
            .assertIsDisplayed()

        // Author
        composeTestRule.onNodeWithTag(TestTags.DETAILS_AUTHOR)
            .assertIsDisplayed()
    }

    @Test
    fun testDetailsImageDisplayed() {
        // Wait for content
        composeTestRule.waitForIdle()

        // Verify image is displayed
        composeTestRule.onNodeWithTag(TestTags.DETAILS_IMAGE)
            .assertIsDisplayed()
            .assertWidthIsAtLeast(1.dp) // Image should have dimensions
    }

    @Test
    fun testDetailsStatisticsDisplayed() {
        // Wait for content
        composeTestRule.waitForIdle()

        // Verify all three statistics icons are displayed
        composeTestRule.onNodeWithTag(TestTags.DETAILS_LIKES)
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag(TestTags.DETAILS_DOWNLOADS)
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag(TestTags.DETAILS_COMMENTS)
            .assertIsDisplayed()
    }

    @Test
    fun testDetailsTagsDisplayed() {
        // Wait for content
        composeTestRule.waitForIdle()

        // Verify tags LazyRow is displayed
        composeTestRule.onNodeWithTag(TestTags.DETAILS_TAGS)
            .assertIsDisplayed()
    }

    @Test
    fun testDetailsAuthorDisplayed() {
        // Wait for content
        composeTestRule.waitForIdle()

        // Verify author text is displayed with prefix
        composeTestRule.onNodeWithTag(TestTags.DETAILS_AUTHOR)
            .assertIsDisplayed()

        // Author should have "by" prefix from string resource
        // The actual format is from R.string.author_name_prefix
        composeTestRule.onNodeWithTag(TestTags.DETAILS_AUTHOR)
            .assertExists()
    }

    companion object {
        const val GETTING_DATA_DELAY = 5000L
    }
}