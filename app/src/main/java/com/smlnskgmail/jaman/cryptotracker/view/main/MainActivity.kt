package com.smlnskgmail.jaman.cryptotracker.view.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.smlnskgmail.jaman.cryptotracker.BuildConfig
import com.smlnskgmail.jaman.cryptotracker.R
import com.smlnskgmail.jaman.cryptotracker.components.activities.BaseThemeActivity
import com.smlnskgmail.jaman.cryptotracker.components.preferences.PreferencesManager
import com.smlnskgmail.jaman.cryptotracker.components.preferences.Theme
import com.smlnskgmail.jaman.cryptotracker.model.api.cache.CurrencyCache
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.Currency
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyApi
import com.smlnskgmail.jaman.cryptotracker.model.impl.cache.mapdb.MapDbCurrencyCache
import com.smlnskgmail.jaman.cryptotracker.model.impl.cache.mapdb.MapDbCurrencySerializer
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcup.CmcCurrencyApi
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcup.cache.CmcCurrencyMapDbInstanceProvider
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.debug.DebugCurrencyApi
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.debug.DebugCurrencyMapDbInstanceProvider
import com.smlnskgmail.jaman.cryptotracker.presenter.list.CurrenciesListPresenter
import com.smlnskgmail.jaman.cryptotracker.presenter.list.CurrenciesListPresenterImpl
import com.smlnskgmail.jaman.cryptotracker.view.info.BottomSheetCurrencyInfo
import com.smlnskgmail.jaman.cryptotracker.view.main.list.CurrenciesAdapter
import com.smlnskgmail.jaman.cryptotracker.view.main.list.CurrencyHolder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseThemeActivity(), CurrencyMainView {

    private lateinit var currenciesListPresenter: CurrenciesListPresenter

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureCurrenciesList()
        configureSwipeRefresh()
        currenciesListPresenter = CurrenciesListPresenterImpl()
        currenciesListPresenter.init(
            this,
            currencyApi(),
            currencyCache()
        )
        currenciesListPresenter.loadCurrenciesFromCache()
        currenciesListPresenter.loadCurrenciesFromWeb()
    }

    private fun configureSwipeRefresh() {
        currencies_list_refresh.isEnabled = false
        currencies_list_refresh.setColorSchemeColors(
            ContextCompat.getColor(
                this,
                R.color.colorAccent
            )
        )
        currencies_list_refresh.setOnRefreshListener {
            currenciesListPresenter.refreshCurrenciesFromWeb()
        }
    }

    private fun configureCurrenciesList() {
        currencies_list.messageView = list_empty_message
    }

    @Suppress("ConstantConditionIf")
    private fun currencyApi(): CurrencyApi {
        return if (BuildConfig.API_IMPL == "DEBUG") {
            DebugCurrencyApi()
        } else {
            CmcCurrencyApi()
        }
    }

    @Suppress("ConstantConditionIf")
    private fun currencyCache(): CurrencyCache {
        val instanceProvider = if (BuildConfig.API_IMPL == "DEBUG") {
            DebugCurrencyMapDbInstanceProvider()
        } else {
            CmcCurrencyMapDbInstanceProvider()
        }
        return MapDbCurrencyCache(
            this,
            MapDbCurrencySerializer(
                instanceProvider
            )
        )
    }

    override fun showCurrencies(
        currencies: List<Currency>
    ) {
        currencies_list.adapter = CurrenciesAdapter(
            currencies.sortedBy { it.currencyType().internalPosition },
            currencyClickTarget(),
            priceRefreshTarget()
        )
        currencies_list_refresh.isEnabled = true
        currencies_list_refresh.isRefreshing = false
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
                currenciesListPresenter.updateCurrencyListingFor(
                    currency
                )
            }
        }
    }

    override fun showFullScreenLoader() {
        list_progress_bar.visibility = View.VISIBLE
    }

    override fun showSeekLoader() {
        top_list_progress_bar.visibility = View.VISIBLE
    }

    override fun hideFullScreenLoader() {
        list_progress_bar.visibility = View.GONE
    }

    override fun hideSeekLoader() {
        top_list_progress_bar.visibility = View.GONE
    }

    override fun showLoadError() {
        showSnackbar(
            getString(R.string.currency_load_error_message)
        )
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(
            findViewById(android.R.id.content),
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun updateCurrency(currency: Currency) {
        (currencies_list.adapter!! as CurrenciesAdapter)
            .refreshCurrency(currency)
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

        val restartIntent = Intent(
            this,
            MainActivity::class.java
        )
        finish()
        startActivity(restartIntent)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(
            R.menu.menu_main,
            menu
        )
        return true
    }

}
