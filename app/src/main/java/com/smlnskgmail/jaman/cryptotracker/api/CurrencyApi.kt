package com.smlnskgmail.jaman.cryptotracker.api

import com.google.gson.GsonBuilder
import com.smlnskgmail.jaman.cryptotracker.api.deserializers.CurrencyListingResponseDeserializer
import com.smlnskgmail.jaman.cryptotracker.api.deserializers.CurrencyResponseDeserializer
import com.smlnskgmail.jaman.cryptotracker.api.responses.CurrencyListingResponse
import com.smlnskgmail.jaman.cryptotracker.api.responses.CurrencyResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CurrencyApi {

    private val gson = GsonBuilder()
        .registerTypeAdapter(
            CurrencyResponse::class.java,
            CurrencyResponseDeserializer()
        )
        .registerTypeAdapter(
            CurrencyListingResponse::class.java,
            CurrencyListingResponseDeserializer()
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
        .build()

    fun configuration(): Retrofit {
        return retrofit
    }

    fun currencyService(): CurrencyService {
        return retrofit.create(
            CurrencyService::class.java
        )
    }

}