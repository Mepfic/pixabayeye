package com.myapps.pixabayeye.screen

import android.os.SystemClock.sleep
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.myapps.pixabayeye.clickOnViewChild
import com.myapps.pixabayeye.details.R
import com.myapps.pixabayeye.details.ui.DetailsFragment
import com.myapps.pixabayeye.details.ui.DetailsFragmentArgs
import com.myapps.pixabayeye.launchActivity
import com.myapps.pixabayeye.launchFragmentInHiltContainer
import com.myapps.pixabayeye.search.adapter.ItemsViewHolder
import com.myapps.pixabayeye.test.common.stub.StubModels.hitModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.myapps.pixabayeye.common.R as CommonR
import com.myapps.pixabayeye.search.R as SearchR

@HiltAndroidTest
class DetailsScreenTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun testLaunchFragment() {
        launchFragmentInHiltContainer<DetailsFragment>(
            DetailsFragmentArgs(hitModel.imageId).toBundle()
        )
        sleep(GETTING_DATA_DELAY)
        onView(withId(R.id.detailsScrollView)).check(matches(isDisplayed()))
    }

    @Test
    fun testUiPopulate() {
        launchFragmentInHiltContainer<DetailsFragment>(
            DetailsFragmentArgs(hitModel.imageId).toBundle()
        )
        val context = ApplicationProvider.getApplicationContext<HiltTestApplication>()
        val expectedAuthorName =
            context.getString(CommonR.string.author_name_prefix, hitModel.userName)
        sleep(GETTING_DATA_DELAY)
        onView(withId(R.id.detailsImage)).check(matches(isDisplayed()))
        onView(withId(R.id.likesText)).check(matches(isDisplayed()))
        onView(withId(R.id.downloadsText)).check(matches(isDisplayed()))
        onView(withId(R.id.commentsText)).check(matches(isDisplayed()))
        onView(withId(R.id.tagsChipGroup)).check(matches(isDisplayed()))
        onView(withId(R.id.nameText)).check(matches(isDisplayed()))
        onView(withId(R.id.likesText)).check(matches(withText(hitModel.likes.toString())))
        onView(withId(R.id.downloadsText)).check(matches(withText(hitModel.downloads.toString())))
        onView(withId(R.id.commentsText)).check(matches(withText(hitModel.comments.toString())))
        onView(withId(R.id.nameText)).check(matches(withText(expectedAuthorName)))
    }

    @Test
    fun testNavigateBack() {
        launchActivity()
        sleep(GETTING_DATA_DELAY)
        onView(withId(com.myapps.pixabayeye.search.R.id.recycler)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ItemsViewHolder>(
                1,
                clickOnViewChild(com.myapps.pixabayeye.search.R.id.previewImage)
            )
        )
        onView(withId(android.R.id.button1)).perform(click())
        onView(withId(R.id.detailsScrollView)).check(matches(isDisplayed()))
        pressBack()
        onView(withId(SearchR.id.searchContainer)).check(matches(isDisplayed()))
    }

    companion object {
        const val GETTING_DATA_DELAY = 15000L
    }
}
