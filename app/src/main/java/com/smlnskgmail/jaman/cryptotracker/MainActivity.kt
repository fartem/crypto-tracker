package com.smlnskgmail.jaman.cryptotracker

import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.smlnskgmail.jaman.cryptotracker.components.BaseThemeActivity
import com.smlnskgmail.jaman.cryptotracker.components.preferences.PreferencesManager
import com.smlnskgmail.jaman.cryptotracker.components.preferences.Theme
import com.smlnskgmail.jaman.cryptotracker.view.list.CurrenciesListFragment

class MainActivity : BaseThemeActivity() {

    companion object {

        private const val currentFragmentTag = "CURRENT_FRAGMENT"

    }

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)
        val currenciesListFragment = CurrenciesListFragment()
        supportFragmentManager.beginTransaction().let {
            it.add(
                R.id.container,
                currenciesListFragment,
                currentFragmentTag
            )
            it.commit()
        }
    }

    override fun onOptionsItemSelected(
        item: MenuItem
    ): Boolean {
        when (item.itemId) {
            R.id.menu_item_theme_change -> changeAppTheme()
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressWarnings("MagicNumber")
    private fun changeAppTheme() {
        val newTheme = if (PreferencesManager.theme(
                this
            ) == Theme.Light) {
            Theme.Dark
        } else {
            Theme.Light
        }

        PreferencesManager.changeTheme(
            this,
            newTheme
        )

        setTheme(newTheme.themeResId)
        applyThemeToCurrentFragment()
        applyTheme(newTheme)
    }

    private fun applyTheme(theme: Theme) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (theme == Theme.Light) {
                var flags = window.decorView.systemUiVisibility
                flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                window.decorView.systemUiVisibility = flags
            } else {
                window.decorView.systemUiVisibility = 0
            }
        }

        val primaryColor = getColorFromAttrs(
            R.attr.colorPrimary
        )
        window.statusBarColor = primaryColor
        supportActionBar?.setBackgroundDrawable(
            ColorDrawable(
                primaryColor
            )
        )
    }

    private fun getColorFromAttrs(
        themeColor: Int
    ): Int {
        val typedValue = TypedValue()
        val theme = theme
        theme.resolveAttribute(
            themeColor,
            typedValue,
            true
        )
        return typedValue.data
    }

    private fun applyThemeToCurrentFragment() {
        val currentFragment = supportFragmentManager.findFragmentByTag(
            currentFragmentTag
        )
        currentFragment?.let {
            supportFragmentManager.beginTransaction().let {
                it.detach(currentFragment)
                it.attach(currentFragment)
                it.commit()
            }
        }
    }

    override fun onPrepareOptionsMenu(
        menu: Menu?
    ): Boolean {
        menuInflater.inflate(
            R.menu.menu_main,
            menu
        )
        return true
    }

    override fun layoutResId(): Int {
        return R.layout.activity_main
    }

}
