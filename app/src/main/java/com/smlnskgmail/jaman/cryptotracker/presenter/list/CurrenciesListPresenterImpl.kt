package com.smlnskgmail.jaman.cryptotracker.presenter.list

import com.smlnskgmail.jaman.cryptotracker.model.api.cache.CurrencyCache
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.Currency
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyApi
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyListing
import com.smlnskgmail.jaman.cryptotracker.view.main.CurrencyMainView

class CurrenciesListPresenterImpl : CurrenciesListPresenter {

    private lateinit var currencyMainView: CurrencyMainView
    private lateinit var currencyApi: CurrencyApi
    private lateinit var currencyCache: CurrencyCache

    private var firstStart = true

    override fun init(
        currencyMainView: CurrencyMainView,
        currencyApi: CurrencyApi,
        currencyCache: CurrencyCache
    ) {
        this.currencyMainView = currencyMainView
        this.currencyApi = currencyApi
        this.currencyCache = currencyCache
    }

    override fun loadCurrenciesFromCache() {
        val currencies = currencyCache.getCurrencies()
        firstStart = currencies.isEmpty()
        if (!firstStart) {
            currencyMainView.showCurrencies(
                currencies
            )
        }
    }

    override fun loadCurrenciesFromWeb() {
        if (firstStart) {
            currencyMainView.showFullScreenLoader()
        } else {
            currencyMainView.showSeekLoader()
        }
        loadCurrenciesWithAction {
            if (firstStart) {
                currencyMainView.hideFullScreenLoader()
            } else {
                currencyMainView.hideSeekLoader()
            }
        }
    }

    private fun loadCurrenciesWithAction(action: () -> Unit) {
        currencyApi.currencies(object : CurrencyApi.CurrenciesLoadResult {
            override fun loaded(currencies: List<Currency>) {
                if (currencies.isNotEmpty()) {
                    currencyMainView.showCurrencies(
                        currencies
                    )
                    currencyCache.clear()
                    currencyCache.putCurrencies(currencies)
                } else {
                    currencyMainView.showLoadError()
                }
                action()
            }
        })
    }

    override fun refreshCurrenciesFromWeb() {
        loadCurrenciesWithAction { }
    }

    override fun updateCurrencyListingFor(currency: Currency) {
        currencyApi.currencyListing(
            currency,
            object : CurrencyApi.CurrencyListingLoadResult {
                override fun loaded(currencyListing: CurrencyListing?) {
                    if (currencyListing != null) {
                        currency.updateCurrencyListing(
                            currencyListing
                        )
                        currencyMainView.updateCurrency(currency)
                        currencyCache.updateCurrency(currency)
                    } else {
                        currencyMainView.showLoadError()
                    }
                }
            }
        )
    }

}
