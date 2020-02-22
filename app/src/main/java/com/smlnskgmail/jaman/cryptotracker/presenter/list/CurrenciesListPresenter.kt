package com.smlnskgmail.jaman.cryptotracker.presenter.list

import com.smlnskgmail.jaman.cryptotracker.model.api.cache.CurrencyCache
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.Currency
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyApi
import com.smlnskgmail.jaman.cryptotracker.view.list.CurrenciesListView

interface CurrenciesListPresenter {

    fun init(
        currenciesListView: CurrenciesListView,
        currencyApi: CurrencyApi,
        currencyCache: CurrencyCache
    )

    fun refreshCurrencies()

    fun currencySelected(
        currency: Currency
    )

    fun updateCurrencyListingFor(
        currency: Currency
    )

}
