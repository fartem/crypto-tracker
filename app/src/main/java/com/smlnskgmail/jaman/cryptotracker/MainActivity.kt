package com.smlnskgmail.jaman.cryptotracker

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import com.smlnskgmail.jaman.cryptotracker.components.activities.BaseActivity
import com.smlnskgmail.jaman.cryptotracker.components.preferences.PreferencesManager
import com.smlnskgmail.jaman.cryptotracker.components.preferences.Theme
import com.smlnskgmail.jaman.cryptotracker.logic.api.CurrencyApi
import com.smlnskgmail.jaman.cryptotracker.logic.api.coinmarketcup.CmcCurrencyApi
import com.smlnskgmail.jaman.cryptotracker.logic.api.entities.Currency
import com.smlnskgmail.jaman.cryptotracker.logic.api.entities.CurrencyListing
import com.smlnskgmail.jaman.cryptotracker.logic.currencieslist.CurrenciesAdapter
import com.smlnskgmail.jaman.cryptotracker.logic.currencieslist.CurrencyHolder
import com.smlnskgmail.jaman.cryptotracker.logic.currencyinfo.BottomSheetCurrencyInfo
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_empty_message.*
import kotlinx.android.synthetic.main.list_progress_bar.*

class MainActivity : BaseActivity() {

    private lateinit var api: CurrencyApi

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showLoader()
        loadCurrencies()
    }

    private fun showLoader() {
        currencies_list.withMessage(list_empty_message)
        currencies_list.withProgressBar(list_progress_bar)
    }

    private fun loadCurrencies() {
        api = CmcCurrencyApi()
        api.currencies(object : CurrencyApi.CurrenciesLoadResult {
            override fun loaded(currencies: List<Currency>) {
                if (currencies.isNotEmpty()) {
                    showCurrenciesList(currencies)
                } else {
                    showLoaderError()
                }
            }
        })
    }

    private fun showLoaderError() {
        createList(emptyList())
    }

    private fun showCurrenciesList(
        currencies: List<Currency>
    ) {
        createList(currencies)
    }

    private fun createList(
        currencies: List<Currency>
    ) {
        currencies_list.adapter = CurrenciesAdapter(
            currencies,
            currencyClickTarget(),
            priceRefreshTarget()
        )
    }

    private fun currencyClickTarget(): CurrencyHolder.CurrencyClickTarget {
        return object : CurrencyHolder.CurrencyClickTarget {
            override fun onCurrencyClick(currency: Currency) {
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
        }
    }

    private fun priceRefreshTarget(): CurrencyHolder.CurrencyRefreshClickTarget {
        return object : CurrencyHolder.CurrencyRefreshClickTarget {
            override fun onCurrencyRefreshClick(currency: Currency) {
                api.currencyListing(
                    currency.id,
                    object : CurrencyApi.CurrencyListingLoadResult {
                        override fun loaded(currencyListing: CurrencyListing?) {
                            if (currencyListing != null) {
                                currency.listing = currencyListing
                                (currencies_list.adapter!! as CurrenciesAdapter)
                                    .refreshCurrency(currency)
                            } else {
                                showSnackbar(
                                    getString(R.string.currency_load_error_message)
                                )
                            }
                        }
                    }
                )
            }
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
        startActivity(restartIntent)
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

    private fun showSnackbar(message: String) {
        Snackbar.make(
            findViewById(android.R.id.content),
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun isFullScreen(): Boolean {
        return false
    }

}
