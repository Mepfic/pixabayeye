package com.myapps.pixabayeye

import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher

fun typeSearchViewText(text: String): ViewAction {
    return object : ViewAction {
        override fun getDescription() = "Change view text"

        override fun getConstraints(): Matcher<View> =
            CoreMatchers.allOf(
                ViewMatchers.isDisplayed(),
                ViewMatchers.isAssignableFrom(SearchView::class.java)
            )

        override fun perform(uiController: UiController?, view: View?) {
            (view as SearchView).setQuery(text, true)
        }
    }
}

fun clickOnViewChild(viewId: Int) = object : ViewAction {
    override fun getDescription() = "Click on a child view with specified id."

    override fun getConstraints() = null

    override fun perform(uiController: UiController, view: View) =
        ViewActions.click().perform(uiController, view.findViewById<View>(viewId))
}
