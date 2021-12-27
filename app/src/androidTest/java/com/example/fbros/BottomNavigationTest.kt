package com.example.fbros

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.NoActivityResumedException
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers.allOf
import org.junit.Assert.fail
import org.junit.Rule
import org.junit.Test

class BottomNavigationTest {

    @get:Rule var instantTaskExecutor = InstantTaskExecutorRule()

    @get:Rule var activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun bottomNav_clickOnAllItems(){
        assertFirstScreen()

        openThirdScreen()

        assertThirdScreen()

        openSecondScreen()

        asserSecondScreen()

        openFirstScreen()

        assertFirstScreen()
    }


    @Test
    fun bottomNav_backGoesToFirst(){
        openThirdScreen()

        pressBack()

        assertFirstScreen()
    }

    @Test(expected = NoActivityResumedException::class)
    fun backFromFirstExits(){
        assertFirstScreen()

        pressBack()

        fail()
    }

    private fun openFirstScreen() {
        onView(allOf(withContentDescription(R.string.text_label_employee)
            , isDisplayed(), withId(R.id.employee)))
            .perform(click())
    }

    private fun assertFirstScreen() {
        onView(withId(R.id.recycler_employees)).check(matches(isDisplayed()))
    }

    private fun openSecondScreen() {
        onView(allOf(withContentDescription(R.string.text_label_routes)
            , isDisplayed(), withId(R.id.route)))
            .perform(click()
            )
    }

    private fun asserSecondScreen() {
        onView(withId(R.id.recycler_routes)).check(matches(isDisplayed()))
    }

    private fun openThirdScreen() {
        onView(allOf(withContentDescription(R.string.text_label_stops)
            , isDisplayed(), withId(R.id.stop)))
            .perform(click())
    }

    private fun assertThirdScreen() {
        onView(withId(R.id.recycler_stops)).check(matches(isDisplayed()))
    }

}