package com.smlnskgmail.jaman.cryptotracker.components.preferences

import com.smlnskgmail.jaman.cryptotracker.R

enum class Theme(
    val themeResId: Int,
    val fullScreenThemeResId: Int
) {

    Light(
        R.style.AppTheme,
        R.style.AppFullScreenTheme
    ),
    Dark(
        R.style.AppThemeDark,
        R.style.AppFullScreenThemeDark
    )

}
