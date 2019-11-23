package com.smlnskgmail.jaman.cryptotracker.coinmarketcap.api.responses

import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.api.ApiResponseStatus
import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.model.Currency

class CurrencyResponse(

    val currencies: List<Currency>,
    apiResponseStatus: ApiResponseStatus

)