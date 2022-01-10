package com.example.gharmai

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import com.example.gharmai.UI.UserRegister
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@LargeTest
@RunWith(JUnit4::class)
class RegisterTest {
    @get:Rule
    val testRule1 = ActivityScenarioRule(UserRegister::class.java)
    @Test
    fun registerCheck() {

        Espresso.onView(withId(R.id.username))
            .perform(ViewActions.typeText("admin"))
        Thread.sleep(1000)
        Espresso.closeSoftKeyboard()

        Espresso.onView(withId(R.id.userEmail))
            .perform(ViewActions.typeText("admin@gmail.com"))
        Espresso.closeSoftKeyboard()
        Thread.sleep(1000)


        Espresso.onView(withId(R.id.userPassword))
            .perform(ViewActions.typeText("password"))
        Espresso.closeSoftKeyboard()
        Thread.sleep(1000)

        Espresso.onView(withId(R.id.confirmPassword))
            .perform(ViewActions.typeText("password"))
        Espresso.closeSoftKeyboard()
        Thread.sleep(1000)

        Espresso.onView(withId(R.id.userAddress))
            .perform(ViewActions.typeText("Dillibazar"))
        Espresso.closeSoftKeyboard()
        Thread.sleep(1000)

        Espresso.onView(withId(R.id.userPhone))
            .perform(ViewActions.typeText("9873527495"))
        Espresso.closeSoftKeyboard()
        Thread.sleep(1000)



        Espresso.onView(withId(R.id.userSignUp)).perform(ViewActions.click())

        Thread.sleep(2000)

        val checkText =
            Espresso.onView(Matchers.allOf(withId(R.id.linearLayout), ViewMatchers.isDisplayed()))
        checkText.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Thread.sleep(5000)
    }
}