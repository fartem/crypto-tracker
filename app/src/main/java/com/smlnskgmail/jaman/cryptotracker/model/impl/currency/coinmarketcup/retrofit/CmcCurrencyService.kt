package com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcup.retrofit

import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcup.retrofit.responses.CurrencyListingResponse
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcup.retrofit.responses.CurrencyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CmcCurrencyService {

    @GET("v1/cryptocurrency/quotes/latest")
    @Headers(
        "X-CMC_PRO_API_KEY: API_KEY",
        "Accept: application/json"
    )
    fun currencies(
        @Query("symbol") currencies: String
    ): Call<CurrencyResponse>

    @GET("v1/cryptocurrency/quotes/latest")
    @Headers(
        "X-CMC_PRO_API_KEY: API_KEY",
        "Accept: application/json"
    )
    fun listing(
        @Query("id") currencyId: Int
    ): Call<CurrencyListingResponse>

}
