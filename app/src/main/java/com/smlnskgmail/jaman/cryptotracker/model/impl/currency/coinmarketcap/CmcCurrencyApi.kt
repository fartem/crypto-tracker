package com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcap

import com.google.gson.GsonBuilder
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.Currency
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyApi
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyType
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcap.deserializers.CurrencyListingResponseDeserializer
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcap.deserializers.CurrencyResponseDeserializer
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcap.retrofit.CmcCurrencyService
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcap.retrofit.responses.CurrencyListingResponse
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcap.retrofit.responses.CurrencyResponse
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CmcCurrencyApi : CurrencyApi {

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
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(
                GsonConverterFactory.create(gson)
            )
            .build()
        currencyService = retrofit.create(
            CmcCurrencyService::class.java
        )
    }

    override fun currencies(
        currenciesLoadTarget: CurrencyApi.CurrenciesLoadTarget
    ) {
        currencyService.currencies(
            CurrencyType.supportedSymbols().joinToString(","))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { currenciesLoadTarget.loaded(it.currencies) },
                { currenciesLoadTarget.loaded(emptyList()) }
            )
    }

    override fun currencyListing(
        currency: Currency,
        currencyListingLoadTarget: CurrencyApi.CurrencyListingLoadTarget
    ) {
        currencyService.listing((currency as CmcCurrency).id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { currencyListingLoadTarget.loaded(it.currencyListing) }
    }

}
