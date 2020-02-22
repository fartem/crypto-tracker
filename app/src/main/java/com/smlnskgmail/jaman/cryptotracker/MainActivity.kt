package com.smlnskgmail.jaman.cryptotracker

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import com.jakewharton.processphoenix.ProcessPhoenix
import com.smlnskgmail.jaman.cryptotracker.components.activities.BaseThemeActivity
import com.smlnskgmail.jaman.cryptotracker.components.preferences.PreferencesManager
import com.smlnskgmail.jaman.cryptotracker.components.preferences.Theme
import com.smlnskgmail.jaman.cryptotracker.view.list.CurrenciesListFragment

class MainActivity : BaseThemeActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)
        val currenciesListFragment = CurrenciesListFragment()
        supportFragmentManager
            .beginTransaction()
            .add(
                R.id.container,
                currenciesListFragment,
                currenciesListFragment.javaClass.canonicalName
            )
            .commit()
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
            ) == Theme.Light
        ) {
            Theme.Dark
        } else {
            Theme.Light
        }

        PreferencesManager.changeTheme(
            this,
            newTheme
        )

        Handler().postDelayed({
            ProcessPhoenix.triggerRebirth(
                this,
                Intent(
                    this,
                    MainActivity::class.java
                )
            )
        }, 100)
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
