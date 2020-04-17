package com.smlnskgmail.jaman.cryptotracker.theme

import androidx.preference.PreferenceManager
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.smlnskgmail.jaman.cryptotracker.MainActivity
import com.smlnskgmail.jaman.cryptotracker.R
import com.smlnskgmail.jaman.cryptotracker.components.preferences.PreferencesManager
import com.smlnskgmail.jaman.cryptotracker.components.preferences.Theme
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep

@RunWith(AndroidJUnit4::class)
class ThemeChangeTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(
        MainActivity::class.java
    )

    @Before
    fun clearPreferences() {
        PreferenceManager.getDefaultSharedPreferences(
            activityTestRule.activity
        ).edit().clear().apply()
    }

    @Test
    fun changeThemeTest() {
        val defaultTheme = PreferencesManager.theme(
            activityTestRule.activity
        )
        assertEquals(
            Theme.Light,
            defaultTheme
        )

        changeTheme()
        delay()

        var newTheme = PreferencesManager.theme(
            activityTestRule.activity
        )
        assertEquals(
            Theme.Dark,
            newTheme
        )

        changeTheme()
        delay()

        newTheme = PreferencesManager.theme(
            activityTestRule.activity
        )
        assertEquals(
            Theme.Light,
            newTheme
        )
    }

    private fun changeTheme() {
        try {
            onView(withId(R.id.menu_item_theme_change)).perform(click())
        } catch (e: NoMatchingViewException) {
            openActionBarOverflowOrOptionsMenu(
                activityTestRule.activity
            )
            onView(withId(R.id.menu_item_theme_change)).perform(click())
        }
    }

    private fun delay() {
        sleep(3_000)
    }

}
