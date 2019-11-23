package com.smlnskgmail.jaman.cryptotracker

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.CurrencyListLoader
import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.CurrencyListLoaderTarget
import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.api.CurrencyApi
import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.list.CurrenciesAdapter
import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.list.holder.HolderClickTarget
import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.list.info.BottomSheetCurrencyInfo
import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.model.Currency
import com.smlnskgmail.jaman.cryptotracker.logger.L
import com.smlnskgmail.jaman.cryptotracker.preferences.PreferencesManager
import com.smlnskgmail.jaman.cryptotracker.preferences.theme.Theme
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_empty_message.*
import kotlinx.android.synthetic.main.list_progress_bar.*

class MainActivity : AppCompatActivity(), CurrencyListLoaderTarget, HolderClickTarget {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        setTheme(
            PreferencesManager.theme(
                this
            ).themeResId
        )
        super.onCreate(
            savedInstanceState
        )
        setContentView(
            R.layout.activity_main
        )
        showLoader()
        loadCurrencies()
    }

    private fun showLoader() {
        currencies_list.withMessage(
            list_empty_message
        )
        currencies_list.withProgressBar(
            list_progress_bar
        )
    }

    private fun loadCurrencies() {
        val api = CurrencyApi()
        api.initWithCache(
            this
        ) {
            isOnline()
        }

        CurrencyListLoader(
            api,
            this
        ).loadCurrencies()
    }

    override fun loaderResult(
        currencies: List<Currency>,
        throwable: Throwable?
    ) {
        if (throwable != null) {
            showLoaderError(
                throwable
            )
        } else {
            showCurrenciesList(
                currencies
            )
        }
    }

    private fun showLoaderError(
        throwable: Throwable
    ) {
        createList(
            emptyList()
        )
        L.e(
            throwable
        )
    }

    private fun showCurrenciesList(
        currencies: List<Currency>
    ) {
        if (currencies.isNotEmpty() && !isOnline()) {
            showSnackbar(
                getString(
                    R.string.currency_list_device_is_offline
                )
            )
        }
        createList(
            currencies
        )
    }

    private fun createList(
        currencies: List<Currency>
    ) {
        currencies_list.adapter = CurrenciesAdapter(
            currencies,
            this
        )
    }

    override fun holderItemClick(
        currency: Currency
    ) {
        val bundle = Bundle()
        bundle.putSerializable(
            "currency",
            currency
        )

        val bottomSheetCurrencyInfo = BottomSheetCurrencyInfo()
        bottomSheetCurrencyInfo.arguments = bundle
        bottomSheetCurrencyInfo.show(
            supportFragmentManager,
            BottomSheetCurrencyInfo::class.java.canonicalName
        )
    }

    override fun onOptionsItemSelected(
        item: MenuItem
    ): Boolean {
        when (item.itemId) {
            R.id.menu_item_theme_change -> changeAppTheme()
        }
        return super.onOptionsItemSelected(
            item
        )
    }

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

        val restartIntent = Intent(
            this,
            MainActivity::class.java
        )
        finish()
        startActivity(
            restartIntent
        )
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

    @Suppress(
        "DEPRECATION"
    )
    private fun isOnline(): Boolean {
        val connectivityManager = getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnected!!
    }

    private fun showSnackbar(
        message: String
    ) {
        Snackbar.make(
            findViewById(
                android.R.id.content
            ),
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }

}
