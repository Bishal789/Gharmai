package com.example.gharmai

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import com.example.gharmai.UI.Dashboard
import com.example.gharmai.UI.UserRegister
import com.example.gharmai.UI.User_editProfile
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@LargeTest
@RunWith(JUnit4::class)
class ProfileTest {
    @get:Rule
    val testRule1 = ActivityScenarioRule(Dashboard::class.java)
    @Test
    fun profileUpdate() {
        Espresso.onView(ViewMatchers.withId(R.id.profile)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.menuProfile)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.edit_profile)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.editProfile)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.etUsername))
            .perform(ViewActions.typeText("Anmol K.C."))
        Espresso.closeSoftKeyboard()
        Thread.sleep(1000)

        Espresso.onView(ViewMatchers.withId(R.id.etAddress))
            .perform(ViewActions.typeText("Baneshwor"))
        Espresso.closeSoftKeyboard()
        Thread.sleep(1000)

        val checkText =
            Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.constraint), ViewMatchers.isDisplayed()))
        checkText.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(5000)
    }

//    fun profiledelete() {
//        Espresso.onView(ViewMatchers.withId(R.id.profile)).perform(ViewActions.click())
//        Espresso.onView(ViewMatchers.withId(R.id.menuProfile)).perform(ViewActions.click())
//        Espresso.onView(ViewMatchers.withId(R.id.delete_profile)).perform(ViewActions.click())
//
//        val checkText =
//            Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.linearLayout), ViewMatchers.isDisplayed()))
//        checkText.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//        Thread.sleep(5000)
//    }
}