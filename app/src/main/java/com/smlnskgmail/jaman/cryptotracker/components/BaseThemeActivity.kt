package com.smlnskgmail.jaman.cryptotracker.components

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.smlnskgmail.jaman.cryptotracker.components.preferences.PreferencesManager

abstract class BaseThemeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        if (isFullScreen()) {
            PreferencesManager.theme(
                this
            ).fullScreenThemeResId
        } else {
            PreferencesManager.theme(
                this
            ).themeResId
        }.let {
            setTheme(it)
        }
        super.onCreate(savedInstanceState)
        setContentView(layoutResId())
    }

    abstract fun layoutResId(): Int

    open fun isFullScreen(): Boolean {
        return false
    }

}
