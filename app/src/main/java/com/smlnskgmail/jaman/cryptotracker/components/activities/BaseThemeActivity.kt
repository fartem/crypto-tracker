package com.smlnskgmail.jaman.cryptotracker.components.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.smlnskgmail.jaman.cryptotracker.components.preferences.PreferencesManager

abstract class BaseThemeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val theme = if (isFullScreen()) {
            PreferencesManager.theme(this).fullScreenThemeResId
        } else {
            PreferencesManager.theme(this).themeResId
        }
        setTheme(theme)
        super.onCreate(savedInstanceState)
    }

    abstract fun isFullScreen(): Boolean

}
