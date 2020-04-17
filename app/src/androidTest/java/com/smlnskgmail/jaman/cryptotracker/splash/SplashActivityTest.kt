package com.smlnskgmail.jaman.cryptotracker.splash

import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.smlnskgmail.jaman.cryptotracker.MainActivity
import com.smlnskgmail.jaman.cryptotracker.components.splash.SplashActivity
import com.smlnskgmail.jaman.cryptotracker.support.ElapsedTimeIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SplashActivityTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(
        SplashActivity::class.java,
        false,
        false
    )

    private val splashTime = 1_500L

    @Before
    fun setup() {
        Intents.init()
    }

    @After
    fun destroy() {
        Intents.release()
    }

    @Test
    fun checkSplashActivity() {
        activityTestRule.launchActivity(null)

        val idleResource = ElapsedTimeIdlingResource(
            splashTime
        )
        IdlingRegistry.getInstance().register(idleResource)

        intended(hasComponent(MainActivity::class.java.name))

        IdlingRegistry.getInstance().unregister(idleResource)
    }

}
