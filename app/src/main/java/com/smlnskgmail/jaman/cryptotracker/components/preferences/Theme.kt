package com.smlnskgmail.jaman.cryptotracker.components.preferences

import com.smlnskgmail.jaman.cryptotracker.R

enum class Theme(
    val themeResId: Int,
    val logoThemeResId: Int
) {

    Light(
        R.style.AppTheme,
        R.style.AppLogoTheme
    ),
    Dark(
        R.style.AppThemeDark,
        R.style.AppLogoThemeDark
    )

}
