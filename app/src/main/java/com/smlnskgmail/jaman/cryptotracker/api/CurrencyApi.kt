package com.smlnskgmail.jaman.cryptotracker.api

import com.google.gson.GsonBuilder
import com.smlnskgmail.jaman.cryptotracker.api.desirealizers.CurrencyResponseDesirealizer
import com.smlnskgmail.jaman.cryptotracker.api.responses.CurrencyResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CurrencyApi {

    private val gson = GsonBuilder()
        .registerTypeAdapter(
            CurrencyResponse::class.java,
            CurrencyResponseDesirealizer()
        )
        .setLenient()
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(
            "https://pro-api.coinmarketcap.com/"
        )
        .addConverterFactory(
            GsonConverterFactory.create(
                gson
            )
        )
        .addConverterFactory(
            GsonConverterFactory.create(

            )
        )
        .build()

    fun coinService(): CurrencyService {
        return retrofit.create(
            CurrencyService::class.java
        )
    }

}