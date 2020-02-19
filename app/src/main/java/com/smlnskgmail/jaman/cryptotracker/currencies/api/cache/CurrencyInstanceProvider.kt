package com.smlnskgmail.jaman.cryptotracker.currencies.api.cache

import com.smlnskgmail.jaman.cryptotracker.currencies.api.Currency

interface CurrencyInstanceProvider {

    fun currencyTo(
        writer: Any,
        currency: Currency
    )
    fun currencyFrom(reader: Any): Currency

}
