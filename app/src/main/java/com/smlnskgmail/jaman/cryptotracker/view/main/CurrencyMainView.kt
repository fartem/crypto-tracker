package com.smlnskgmail.jaman.cryptotracker.view.main

import com.smlnskgmail.jaman.cryptotracker.model.api.currency.Currency

interface CurrencyMainView {

    fun showCurrencies(
        currencies: List<Currency>
    )

    fun showFullScreenLoader()
    fun showSeekLoader()

    fun hideFullScreenLoader()
    fun hideSeekLoader()

    fun showLoadError()

    fun updateCurrency(
        currency: Currency
    )

}
