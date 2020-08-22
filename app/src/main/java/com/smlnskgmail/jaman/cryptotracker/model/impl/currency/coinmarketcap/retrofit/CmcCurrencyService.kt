package com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcap.retrofit

import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcap.retrofit.responses.CurrencyListingResponse
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcap.retrofit.responses.CurrencyResponse
import io.reactivex.rxjava3.core.Observable
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
    ): Observable<CurrencyResponse>

    @GET("v1/cryptocurrency/quotes/latest")
    @Headers(
        "X-CMC_PRO_API_KEY: API_KEY",
        "Accept: application/json"
    )
    fun listing(
        @Query("id") currencyId: Int
    ): Observable<CurrencyListingResponse>

}
