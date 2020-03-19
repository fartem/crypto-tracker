package com.smlnskgmail.jaman.cryptotracker.components.preferences

import android.content.Context
import androidx.preference.PreferenceManager

object PreferencesManager {

    fun theme(context: Context): Theme {
        return Theme.valueOf(
            stringPreference(
                context,
                Theme::class.java.name,
                Theme.Light.toString()
            )
        )
    }

    fun changeTheme(
        context: Context,
        theme: Theme
    ) {
        saveStringPreference(
            context,
            Theme::class.java.name,
            theme.toString()
        )
    }

    private fun saveStringPreference(
        context: Context,
        key: String,
        value: String
    ) {
        PreferenceManager.getDefaultSharedPreferences(
            context
        ).edit().putString(
            key,
            value
        ).apply()
    }

    private fun stringPreference(
        context: Context,
        key: String,
        defaultValue: String
    ): String {
        return PreferenceManager.getDefaultSharedPreferences(
            context
        ).getString(
            key,
            defaultValue
        )!!
    }

}
