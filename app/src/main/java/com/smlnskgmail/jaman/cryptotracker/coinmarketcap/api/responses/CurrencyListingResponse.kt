package com.smlnskgmail.jaman.cryptotracker.coinmarketcap.api.responses

import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.api.ApiResponseStatus
import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.model.CurrencyListing

class CurrencyListingResponse(

    val currencyListing: CurrencyListing,
    val apiResponseStatus: ApiResponseStatus

)