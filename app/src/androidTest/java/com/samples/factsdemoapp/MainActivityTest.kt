package com.samples.factsdemoapp

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.samples.factsdemoapp.ui.MainActivity
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Test MainActivity
 *
 * @author AkashG
 * @since 20-07-2019.
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Test
    fun testEvent() {
        // start up Main screen
        val scenario = ActivityScenario.launch(MainActivity::class.java)
        // Click on the error retry button
        onView(withId(R.id.txt_error)).perform(click())
    }

}