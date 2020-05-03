package com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcap.retrofit.responses

import com.smlnskgmail.jaman.cryptotracker.model.api.currency.Currency

data class CurrencyResponse(
    val currencies: List<Currency>
)
