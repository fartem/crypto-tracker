package com.smlnskgmail.jaman.cryptotracker.logic.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.smlnskgmail.jaman.cryptotracker.BuildConfig
import com.smlnskgmail.jaman.cryptotracker.MainActivity
import com.smlnskgmail.jaman.cryptotracker.R
import com.smlnskgmail.jaman.cryptotracker.components.activities.BaseActivity
import com.smlnskgmail.jaman.cryptotracker.components.preferences.PreferencesManager
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {

    companion object {

        private const val logoTime = 1500L

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(
            PreferencesManager.theme(this).fullScreenThemeResId
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        showLogo()
        showAppVersion()
    }

    private fun showLogo() {
        Handler().postDelayed(
            {
                val appIntent = Intent(
                    this,
                    MainActivity::class.java
                )
                startActivity(appIntent)
                finish()
            },
            logoTime
        )
    }

    private fun showAppVersion() {
        logo_version.text = BuildConfig.VERSION_NAME
    }

    override fun isFullScreen(): Boolean {
        return true
    }

}
