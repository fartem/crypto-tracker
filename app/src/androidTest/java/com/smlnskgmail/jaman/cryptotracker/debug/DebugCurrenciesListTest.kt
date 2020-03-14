package com.smlnskgmail.jaman.cryptotracker.debug

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.smlnskgmail.jaman.cryptotracker.BuildConfig
import com.smlnskgmail.jaman.cryptotracker.MainActivity
import com.smlnskgmail.jaman.cryptotracker.R
import com.smlnskgmail.jaman.cryptotracker.support.RecyclerViewCountAssertion
import com.smlnskgmail.jaman.cryptotracker.support.RecyclerViewItemClick
import com.smlnskgmail.jaman.cryptotracker.view.list.recycler.CurrencyHolder
import org.junit.Assert.fail
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep

@RunWith(AndroidJUnit4::class)
class DebugCurrenciesListTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(
        MainActivity::class.java
    )

    @Suppress("ConstantConditionIf")
    @Test
    fun loadCurrenciesTest() {
        if (BuildConfig.API != "DEBUG") {
            fail(
                "You must run this test in DEBUG API!"
            )
        }

        sleep(3_000)
        onView(withId(R.id.currencies_list)).check(
            RecyclerViewCountAssertion(10)
        )

        onView(withId(R.id.currencies_list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<CurrencyHolder>(
                0,
                RecyclerViewItemClick(R.id.currency_item)
            )
        )
    }

}
