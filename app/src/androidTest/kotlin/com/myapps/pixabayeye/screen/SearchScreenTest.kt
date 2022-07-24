package com.myapps.pixabayeye.screen

import android.os.SystemClock.sleep
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.myapps.pixabayeye.clickOnViewChild
import com.myapps.pixabayeye.launchActivity
import com.myapps.pixabayeye.launchFragmentInHiltContainer
import com.myapps.pixabayeye.search.R
import com.myapps.pixabayeye.search.adapter.ItemsViewHolder
import com.myapps.pixabayeye.search.ui.SearchFragment
import com.myapps.pixabayeye.typeSearchViewText
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import com.myapps.pixabayeye.common.R as CommonR
import com.myapps.pixabayeye.details.R as DetailsR

@ExperimentalCoroutinesApi
@HiltAndroidTest
class SearchScreenTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    fun testLaunchFragment() {
        launchFragmentInHiltContainer<SearchFragment>()
        checkUiFlowAfterRequest()
    }

    @Test
    fun testSwipeToRefresh() {
        launchFragmentInHiltContainer<SearchFragment>()
        sleep(GETTING_DATA_DELAY)
        onView(withId(R.id.searchContainer)).perform(swipeDown())
        checkUiFlowAfterRequest()
    }

    @Test
    fun testSearchView() {
        launchFragmentInHiltContainer<SearchFragment>()
        onView(withId(R.id.searchView)).perform(typeSearchViewText(TEST_QUERY))
        checkUiFlowAfterRequest()
    }

    @Test
    fun testAlertDialog() {
        launchActivity()
        sleep(GETTING_DATA_DELAY)
        onView(withId(R.id.recycler)).perform(
            actionOnItemAtPosition<ItemsViewHolder>(
                ITEM_POSITION,
                clickOnViewChild(R.id.previewImage)
            )
        )
        onView(withId(android.R.id.message))
            .inRoot(isDialog())
            .check(matches(withText("")))
            .check(matches(not(isDisplayed())))
        onView(withId(android.R.id.button2))
            .inRoot(isDialog())
            .check(matches(withText(CommonR.string.dialog_negative_button)))
            .check(matches(isDisplayed()))
        onView(withId(android.R.id.button1))
            .inRoot(isDialog())
            .check(matches(withText(CommonR.string.dialog_positive_button)))
            .check(matches(isDisplayed()))
        onView(withId(android.R.id.button2)).perform(click())
        onView(withId(R.id.recycler)).perform(
            actionOnItemAtPosition<ItemsViewHolder>(
                ITEM_POSITION,
                clickOnViewChild(R.id.previewImage)
            )
        )
        onView(withId(android.R.id.button1)).perform(click())
    }

    @Test
    fun testNavigateToDetails() {
        launchActivity()
        sleep(GETTING_DATA_DELAY)
        onView(withId(R.id.recycler)).perform(
            actionOnItemAtPosition<ItemsViewHolder>(
                ITEM_POSITION,
                clickOnViewChild(R.id.previewImage)
            )
        )
        onView(withId(android.R.id.button1)).perform(click())
        onView(withId(DetailsR.id.detailsScrollView)).check(matches(isDisplayed()))
    }

    private fun checkUiFlowAfterRequest() {
        onView(withId(R.id.progressContainer)).check(matches(isDisplayed()))
        onView(withId(R.id.searchView)).check(matches(isDisplayed()))
        sleep(GETTING_DATA_DELAY)
        onView(withId(R.id.progressContainer)).check(matches(not(isDisplayed())))
        onView(withId(R.id.searchView)).check(matches(isDisplayed()))
        onView(withId(R.id.recycler)).check(matches(isDisplayed()))
    }

    companion object {
        const val GETTING_DATA_DELAY = 3000L
        const val TEST_QUERY = "cats"
        const val ITEM_POSITION = 3
    }
}
