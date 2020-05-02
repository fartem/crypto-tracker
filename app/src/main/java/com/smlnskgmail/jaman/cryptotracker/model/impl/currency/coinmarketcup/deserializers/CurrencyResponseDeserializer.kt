package com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcup.deserializers

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.Currency
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyPricePercentChangeDay
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyPrice
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyType
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcup.CmcCurrency
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcup.CmcCurrencyListing
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcup.retrofit.responses.CurrencyResponse
import java.lang.reflect.Type

class CurrencyResponseDeserializer : JsonDeserializer<CurrencyResponse> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): CurrencyResponse {
        val gson = Gson()

        val currencyTypeToken = object : TypeToken<CmcCurrency>() {}.type

        val currencies = arrayListOf<Currency>()

        val dataJson = json!!.asJsonObject.get("data")
        for (data in dataJson.asJsonObject.entrySet()) {
            val currency = gson.fromJson(
                data.value,
                currencyTypeToken
            ) as CmcCurrency

            val currencyAsJson = gson.toJsonTree(data.value)

            val priceInfo = currencyAsJson.asJsonObject
                .get("quote").asJsonObject
                .get("USD")
                .asJsonObject
            val currencyListing = CmcCurrencyListing(
                CurrencyPrice(
                    priceInfo.get(
                        "price"
                    ).asFloat
                ),
                CurrencyPricePercentChangeDay(
                    priceInfo.get(
                        "percent_change_24h"
                    ).asFloat
                )
            )

            currency.currencyListing = currencyListing
            val currencyType = CurrencyType.typeForCurrency(currency)
            if (currencyType != null) {
                currency.currencyType = currencyType
                currencies.add(currency)
            }
        }
        return CurrencyResponse(
            currencies
        )
    }

}
