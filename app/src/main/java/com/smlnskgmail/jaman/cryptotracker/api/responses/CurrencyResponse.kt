package com.smlnskgmail.jaman.cryptotracker.api.responses

import com.smlnskgmail.jaman.cryptotracker.api.ApiResponseStatus
import com.smlnskgmail.jaman.cryptotracker.model.Currency

class CurrencyResponse(

    val currencies: List<Currency>,
    apiResponseStatus: ApiResponseStatus

)