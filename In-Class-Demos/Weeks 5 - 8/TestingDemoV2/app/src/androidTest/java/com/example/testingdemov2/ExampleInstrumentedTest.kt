package com.example.testingdemov2

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get: Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testCounter(){
        composeTestRule.setContent { Counter() }
        composeTestRule.onNodeWithTag("counterDisplay").assertTextEquals("Count: 0")
        composeTestRule.onNodeWithText("Add Count").performClick()
        composeTestRule.onNodeWithTag("counterDisplay").assertTextEquals("Count: 1")

    }

    @Test
    fun testDisappearingSection(){
        composeTestRule.setContent { DisappearingSection() }
        composeTestRule.onNodeWithText("Testing Demo").assertIsDisplayed()
        composeTestRule.mainClock.advanceTimeBy(2500)
        composeTestRule.onNodeWithText("Testing Demo").assertDoesNotExist()
    }
}