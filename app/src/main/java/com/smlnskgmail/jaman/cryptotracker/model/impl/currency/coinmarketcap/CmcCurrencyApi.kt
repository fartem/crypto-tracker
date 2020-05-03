package com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcap

import android.annotation.SuppressLint
import com.google.gson.GsonBuilder
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.Currency
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyApi
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyType
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcap.deserializers.CurrencyListingResponseDeserializer
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcap.deserializers.CurrencyResponseDeserializer
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcap.retrofit.CmcCurrencyService
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcap.retrofit.responses.CurrencyListingResponse
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcap.retrofit.responses.CurrencyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CmcCurrencyApi :
    CurrencyApi {

    private var retrofit: Retrofit

    private var currencyService: CmcCurrencyService

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

    init {
        retrofit = Retrofit.Builder()
            .baseUrl("https://pro-api.coinmarketcap.com/")
            .addConverterFactory(
                GsonConverterFactory.create(gson)
            )
            .build()
        currencyService = retrofit.create(
            CmcCurrencyService::class.java
        )
    }

    override fun currencies(
        currenciesLoadResult: CurrencyApi.CurrenciesLoadResult
    ) {
        currencyService.currencies(
            CurrencyType.supportedSymbols().joinToString(",")
        ).enqueue(
            object : Callback<CurrencyResponse> {
                override fun onFailure(
                    call: Call<CurrencyResponse>,
                    throwable: Throwable
                ) {
                    currenciesLoadResult.loaded(
                        emptyList()
                    )
                }

                override fun onResponse(
                    call: Call<CurrencyResponse>,
                    response: Response<CurrencyResponse>
                ) {
                    val body = response.body()
                    if (body == null) {
                        currenciesLoadResult.loaded(
                            emptyList()
                        )
                    } else {
                        currenciesLoadResult.loaded(
                            body.currencies
                        )
                    }
                }
            }
        )
    }

    override fun currencyListing(
        currency: Currency,
        currencyListingLoadResult: CurrencyApi.CurrencyListingLoadResult
    ) {
        currencyService.listing((currency as CmcCurrency).id).enqueue(
            object : Callback<CurrencyListingResponse> {
                override fun onFailure(
                    call: Call<CurrencyListingResponse>,
                    throwable: Throwable
                ) {
                    currencyListingLoadResult.loaded(
                        null
                    )
                }

                @SuppressLint("SetTextI18n")
                override fun onResponse(
                    call: Call<CurrencyListingResponse>,
                    response: Response<CurrencyListingResponse>
                ) {
                    currencyListingLoadResult.loaded(
                        response.body()!!.currencyListing
                    )
                }
            }
        )
    }

}
