package com.smlnskgmail.jaman.cryptotracker.coinmarketcap.api

import android.content.Context
import com.google.gson.GsonBuilder
import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.api.deserializers.CurrencyListingResponseDeserializer
import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.api.deserializers.CurrencyResponseDeserializer
import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.api.responses.CurrencyListingResponse
import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.api.responses.CurrencyResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CurrencyApi {

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

    fun initWithCache(
        context: Context,
        isOnlinePredicate: () -> Boolean
    ) {
        if (retrofit != null) {
            retrofit = null
        }
        retrofit = Retrofit.Builder()
            .baseUrl("https://pro-api.coinmarketcap.com/")
            .addConverterFactory(
                GsonConverterFactory.create(gson)
            )
            .client(
                HttpClient(context).withLocalCache(
                    isOnlinePredicate
                )
            )
            .build()
    }

    fun currencyService(): CurrencyService {
        return retrofit!!.create(CurrencyService::class.java)
    }

}
