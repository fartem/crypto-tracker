package com.smlnskgmail.jaman.cryptotracker.view.list

import com.smlnskgmail.jaman.cryptotracker.model.api.currency.Currency

interface CurrenciesListView {

    fun showCurrencies(
        currencies: List<Currency>
    )

    fun showFullScreenLoader()
    fun showSeekLoader()

    fun hideFullScreenLoader()
    fun hideSeekLoader()

    fun showLoadError()

    fun showCurrencyInfo(
        currency: Currency
    )
    fun updateCurrency(
        currency: Currency
    )

}
