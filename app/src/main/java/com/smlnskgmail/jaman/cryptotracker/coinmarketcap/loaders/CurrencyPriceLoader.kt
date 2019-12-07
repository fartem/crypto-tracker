package com.smlnskgmail.jaman.cryptotracker.coinmarketcap.loaders

import android.annotation.SuppressLint
import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.api.CurrencyApi
import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.api.responses.CurrencyListingResponse
import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.model.Currency
import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.model.CurrencyListing
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrencyPriceLoader(
    private val api: CurrencyApi,
    private val currency: Currency,
    private val currencyPriceLoaderTarget: CurrencyPriceLoaderTarget
) {

    fun loadPrice() {
        api.currencyService().listing(currency.id).enqueue(
            object : Callback<CurrencyListingResponse> {
                override fun onFailure(
                    call: Call<CurrencyListingResponse>,
                    t: Throwable
                ) {
                    currencyPriceLoaderTarget.currencyPriceLoaderResult(
                        currency,
                        null,
                        t
                    )
                }

                @SuppressLint("SetTextI18n")
                override fun onResponse(
                    call: Call<CurrencyListingResponse>,
                    response: Response<CurrencyListingResponse>
                ) {
                    currencyPriceLoaderTarget.currencyPriceLoaderResult(
                        currency,
                        response.body()!!.currencyListing,
                        null
                    )
                }
            }
        )
    }

    interface CurrencyPriceLoaderTarget {

        fun currencyPriceLoaderResult(
            currency: Currency,
            currencyListing: CurrencyListing?,
            throwable: Throwable?
        )

    }

}