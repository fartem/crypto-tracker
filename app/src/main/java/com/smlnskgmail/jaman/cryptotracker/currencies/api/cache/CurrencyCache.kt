package com.smlnskgmail.jaman.cryptotracker.currencies.api.cache

import com.smlnskgmail.jaman.cryptotracker.currencies.api.Currency

interface CurrencyCache {

    fun putCurrencies(currencies: List<Currency>)
    fun updateCurrency(currency: Currency)

    fun getCurrencies(): List<Currency>

    fun clear()

}
