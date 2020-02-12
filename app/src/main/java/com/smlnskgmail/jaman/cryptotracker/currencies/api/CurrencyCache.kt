package com.smlnskgmail.jaman.cryptotracker.currencies.api

interface CurrencyCache {

    fun putCurrencies(currencies: List<Currency>)
    fun updateCurrency(currency: Currency)

    fun getCurrencies(): List<Currency>

    fun clear()

}
