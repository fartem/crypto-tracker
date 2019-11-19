package com.smlnskgmail.jaman.cryptotracker.api

import android.content.Context
import com.google.gson.GsonBuilder
import com.smlnskgmail.jaman.cryptotracker.api.deserializers.CurrencyListingResponseDeserializer
import com.smlnskgmail.jaman.cryptotracker.api.deserializers.CurrencyResponseDeserializer
import com.smlnskgmail.jaman.cryptotracker.api.responses.CurrencyListingResponse
import com.smlnskgmail.jaman.cryptotracker.api.responses.CurrencyResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CurrencyApi {

    private var retrofit: Retrofit? = null

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

    fun init(
        context: Context
    ) {
        if (retrofit != null) {
            retrofit = null
        }
        retrofit = Retrofit.Builder()
            .baseUrl(
                "https://pro-api.coinmarketcap.com/"
            )
            .addConverterFactory(
                GsonConverterFactory.create(
                    gson
                )
            )
            .client(
                HttpClient(
                    context
                ).withLocalCache()
            )
            .build()
    }

    fun currencyService(): CurrencyService {
        if (retrofit == null) {
            throw RuntimeException(
                "You must need init Retrofit before using service!"
            )
        }
        return retrofit!!.create(
            CurrencyService::class.java
        )
    }

}