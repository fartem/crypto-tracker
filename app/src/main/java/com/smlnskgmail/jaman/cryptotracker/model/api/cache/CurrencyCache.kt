package com.smlnskgmail.jaman.cryptotracker.model.api.cache

import com.smlnskgmail.jaman.cryptotracker.model.api.currency.Currency

interface CurrencyCache {

    fun putCurrencies(currencies: List<Currency>)
    fun updateCurrency(currency: Currency)

    fun getCurrencies(): List<Currency>

    fun clear()

}
