package com.smlnskgmail.jaman.cryptotracker.presenter.list

import com.smlnskgmail.jaman.cryptotracker.model.api.cache.CurrencyCache
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.Currency
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyApi
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyListing
import com.smlnskgmail.jaman.cryptotracker.view.list.CurrenciesListView

class CurrenciesListPresenterImpl : CurrenciesListPresenter {

    private lateinit var currenciesListView: CurrenciesListView
    private lateinit var currencyApi: CurrencyApi
    private lateinit var currencyCache: CurrencyCache

    override fun init(
        currenciesListView: CurrenciesListView,
        currencyApi: CurrencyApi,
        currencyCache: CurrencyCache
    ) {
        this.currenciesListView = currenciesListView
        this.currencyApi = currencyApi
        this.currencyCache = currencyCache
        loadCurrencies()
    }

    private fun loadCurrencies() {
        val currencies = currencyCache.getCurrencies()
        currencies.isEmpty().let { containsCurrencies ->
            if (containsCurrencies) {
                currenciesListView.showFullScreenLoader()
            } else {
                currenciesListView.showCurrencies(
                    currencies
                )
                currenciesListView.showSeekLoader()
            }
            loadCurrenciesWithAction {
                if (containsCurrencies) {
                    currenciesListView.hideFullScreenLoader()
                } else {
                    currenciesListView.hideSeekLoader()
                }
            }
        }
    }

    private fun loadCurrenciesWithAction(
        action: () -> Unit
    ) {
        currencyApi.currencies(object : CurrencyApi.CurrenciesLoadTarget {
            override fun loaded(currencies: List<Currency>) {
                currencies.isNotEmpty().let {  containsCurrencies ->
                    if (containsCurrencies) {
                        currenciesListView.showCurrencies(
                            currencies
                        )
                        currencyCache.clear()
                        currencyCache.putCurrencies(
                            currencies
                        )
                    } else {
                        currenciesListView.showCurrencies(
                            emptyList()
                        )
                        currenciesListView.showLoadError()
                    }
                }
                action()
            }
        })
    }

    override fun refreshCurrencies() {
        loadCurrenciesWithAction { }
    }

    override fun currencySelected(
        currency: Currency
    ) {
        currenciesListView.showCurrencyInfo(
            currency
        )
    }

    override fun updateCurrencyListingFor(
        currency: Currency
    ) {
        currencyApi.currencyListing(
            currency,
            object : CurrencyApi.CurrencyListingLoadTarget {
                override fun loaded(currencyListing: CurrencyListing?) {
                    if (currencyListing != null) {
                        currency.updateCurrencyListing(
                            currencyListing
                        )
                        currenciesListView.updateCurrency(currency)
                        currencyCache.updateCurrency(currency)
                    } else {
                        currenciesListView.showLoadError()
                    }
                }
            }
        )
    }

}
