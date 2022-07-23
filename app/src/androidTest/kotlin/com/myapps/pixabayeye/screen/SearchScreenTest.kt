package com.myapps.pixabayeye.screen

import android.os.SystemClock.sleep
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.myapps.pixabayeye.launchActivity
import com.myapps.pixabayeye.launchFragmentInHiltContainer
import com.myapps.pixabayeye.search.R
import com.myapps.pixabayeye.search.adapter.ItemsViewHolder
import com.myapps.pixabayeye.search.ui.SearchFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
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
    fun testNavigateToDetails() {
        launchActivity()
        sleep(GETTING_DATA_DELAY)
        onView(withId(R.id.recycler)).perform(
            actionOnItemAtPosition<ItemsViewHolder>(ITEM_POSITION, click())
        )
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

    private fun typeSearchViewText(text: String): ViewAction {
        return object : ViewAction {
            override fun getDescription(): String {
                return "Change view text"
            }

            override fun getConstraints(): Matcher<View> {
                return allOf(isDisplayed(), isAssignableFrom(SearchView::class.java))
            }

            override fun perform(uiController: UiController?, view: View?) {
                (view as SearchView).setQuery(text, true)
            }
        }
    }

    companion object {
        const val GETTING_DATA_DELAY = 3000L
        const val TEST_QUERY = "cats"
        const val ITEM_POSITION = 3
    }
}
