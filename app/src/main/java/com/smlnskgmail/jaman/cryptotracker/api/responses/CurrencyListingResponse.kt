package com.smlnskgmail.jaman.cryptotracker.api.responses

import com.smlnskgmail.jaman.cryptotracker.api.ApiResponseStatus
import com.smlnskgmail.jaman.cryptotracker.model.CurrencyListing

class CurrencyListingResponse(

    val currencyListing: CurrencyListing,
    val apiResponseStatus: ApiResponseStatus

)