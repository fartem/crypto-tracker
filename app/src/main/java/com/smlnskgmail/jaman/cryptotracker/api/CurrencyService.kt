package com.smlnskgmail.jaman.cryptotracker.api

import com.smlnskgmail.jaman.cryptotracker.api.responses.CurrencyListingResponse
import com.smlnskgmail.jaman.cryptotracker.api.responses.CurrencyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CurrencyService {

    @GET(
        "v1/cryptocurrency/quotes/latest"
    )
    @Headers(
        "X-CMC_PRO_API_KEY: ",
        "Accept: application/json"
    )
    fun currencyList(
        @Query(
            "symbol"
        ) currencies: String
    ): Call<CurrencyResponse>

    @GET(
        "v1/cryptocurrency/quotes/latest"
    )
    @Headers(
        "X-CMC_PRO_API_KEY: ",
        "Accept: application/json"
    )
    fun listing(
        @Query(
            "id"
        ) currencyId: Int
    ): Call<CurrencyListingResponse>

}