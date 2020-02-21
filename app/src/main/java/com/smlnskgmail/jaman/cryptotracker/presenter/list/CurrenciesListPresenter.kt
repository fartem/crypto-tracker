package com.smlnskgmail.jaman.cryptotracker.presenter.list

import com.smlnskgmail.jaman.cryptotracker.model.api.cache.CurrencyCache
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.Currency
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyApi
import com.smlnskgmail.jaman.cryptotracker.view.main.CurrencyMainView

interface CurrenciesListPresenter {

    fun init(
        currencyMainView: CurrencyMainView,
        currencyApi: CurrencyApi,
        currencyCache: CurrencyCache
    )

    fun loadCurrenciesFromCache()
    fun loadCurrenciesFromWeb()

    fun refreshCurrenciesFromWeb()

    fun updateCurrencyListingFor(
        currency: Currency
    )

}
