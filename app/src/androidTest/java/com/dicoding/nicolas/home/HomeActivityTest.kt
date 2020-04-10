package com.dicoding.nicolas.home

import android.content.Context
import android.widget.EditText
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.dicoding.nicolas.R
import org.junit.Rule
import org.junit.Test

class HomeActivityTest{

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun testAppBehaviour(){
        delay(3000)
        onView(withId(R.id.rvHomeMain)).check(matches(isDisplayed()))
        openActionBarOverflowOrOptionsMenu(
            ApplicationProvider.getApplicationContext<Context>())
        onView(withText("Search Event"))
            .perform(click())
        delay(3000)
        onView(withId(R.id.action_search)).check(matches(isDisplayed()))
        onView(withId(R.id.action_search)).perform(click())
        onView(isAssignableFrom(EditText::class.java)).perform(
            typeText("German"),
            pressImeActionButton(),
            clearText()
        )
        delay(3000)
        onView(isAssignableFrom(EditText::class.java)).perform(
            typeText("Italy"),
            pressImeActionButton(),
            clearText()

        )
        delay(3000)
        onView(isAssignableFrom(EditText::class.java)).perform(
            typeText("Chelsea"),
            pressImeActionButton(),
            clearText()
        )
        pressBack()

    }



    private fun delay(time: Long = 500){
        Thread.sleep(time)
    }
}