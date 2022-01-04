package com.example.gharmai

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import com.example.gharmai.UI.LoginActivity
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@LargeTest
@RunWith(JUnit4::class)
class LoginTest {
    @get:Rule
    val testRule1 = ActivityScenarioRule(LoginActivity::class.java)
    @Test
    fun loginCheck() {

        Espresso.onView(withId(R.id.emailLogin))
            .perform(ViewActions.typeText("123"))
        Thread.sleep(1000)
        Espresso.closeSoftKeyboard()

        Espresso.onView(withId(R.id.passwordLogin))
            .perform(ViewActions.typeText("123"))
        ViewActions.closeSoftKeyboard()
        Thread.sleep(1000)


        Espresso.onView(withId(R.id.button2)).perform(ViewActions.click())

        Thread.sleep(2000)

//        val checkText =
//            Espresso.onView(Matchers.allOf(withId(R.id.drawer), ViewMatchers.isDisplayed()))
//        checkText.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Thread.sleep(5000)
    }
}