package com.smlnskgmail.jaman.cryptotracker.model.api.cache

import com.smlnskgmail.jaman.cryptotracker.model.api.currency.Currency

interface CurrencyInstanceProvider {

    fun currencyTo(
        writer: Any,
        currency: Currency
    )
    fun currencyFrom(reader: Any): Currency

}
