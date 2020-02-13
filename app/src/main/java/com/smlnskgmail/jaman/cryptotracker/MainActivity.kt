package com.smlnskgmail.jaman.cryptotracker

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.smlnskgmail.jaman.cryptotracker.components.activities.BaseThemeActivity
import com.smlnskgmail.jaman.cryptotracker.components.preferences.PreferencesManager
import com.smlnskgmail.jaman.cryptotracker.components.preferences.Theme
import com.smlnskgmail.jaman.cryptotracker.currencies.api.Currency
import com.smlnskgmail.jaman.cryptotracker.currencies.api.CurrencyApi
import com.smlnskgmail.jaman.cryptotracker.currencies.api.CurrencyCache
import com.smlnskgmail.jaman.cryptotracker.currencies.api.CurrencyListing
import com.smlnskgmail.jaman.cryptotracker.currencies.impl.ui.BottomSheetCurrencyInfo
import com.smlnskgmail.jaman.cryptotracker.currencies.impl.ui.currencieslist.CurrenciesAdapter
import com.smlnskgmail.jaman.cryptotracker.currencies.impl.ui.currencieslist.CurrencyHolder
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseThemeActivity() {

    @Inject
    lateinit var currencyApi: CurrencyApi

    @Inject
    lateinit var currencyCache: CurrencyCache

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)
        App.applicationComponent.inject(this)
        setContentView(R.layout.activity_main)
        loadCurrencies()
    }

    private fun loadCurrencies() {
        currencies_list.messageView = list_empty_message
        if (currencyCache.getCurrencies().isEmpty()) {
            list_empty_message.visibility = View.GONE
            currencies_list.visibility = View.GONE
            list_progress_bar.visibility = View.VISIBLE
        } else {
            top_list_progress_bar.visibility = View.VISIBLE
            showCurrenciesList(
                currencyCache.getCurrencies()
            )
        }
        currencyApi.currencies(object : CurrencyApi.CurrenciesLoadResult {
            override fun loaded(currencies: List<Currency>) {
                if (currencies.isNotEmpty()) {
                    currencyCache.clear()
                    currencyCache.putCurrencies(currencies)
                    list_progress_bar.visibility = View.GONE
                    top_list_progress_bar.visibility = View.GONE
                    showCurrenciesList(currencies)
                } else {
                    showLoaderError()
                }
            }
        })
    }

    private fun showLoaderError() {
        showSnackbar(
            getString(R.string.currency_load_error_message)
        )
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
                currencyApi.currencyListing(
                    currency.id(),
                    object : CurrencyApi.CurrencyListingLoadResult {
                        override fun loaded(currencyListing: CurrencyListing?) {
                            if (currencyListing != null) {
                                currency.updateCurrencyListing(currencyListing)
                                (currencies_list.adapter!! as CurrenciesAdapter)
                                    .refreshCurrency(currency)
                                currencyCache.updateCurrency(currency)
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
