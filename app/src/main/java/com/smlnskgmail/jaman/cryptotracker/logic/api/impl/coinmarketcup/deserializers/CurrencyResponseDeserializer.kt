package com.smlnskgmail.jaman.cryptotracker.logic.api.impl.coinmarketcup.deserializers

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import com.smlnskgmail.jaman.cryptotracker.logic.api.Currency
import com.smlnskgmail.jaman.cryptotracker.logic.api.CurrencyListing
import com.smlnskgmail.jaman.cryptotracker.logic.api.CurrencyType
import com.smlnskgmail.jaman.cryptotracker.logic.api.impl.coinmarketcup.retrofit.responses.CurrencyResponse
import java.lang.reflect.Type

class CurrencyResponseDeserializer : JsonDeserializer<CurrencyResponse> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): CurrencyResponse {
        val gson = Gson()

        val currencyTypeToken = object : TypeToken<Currency>() {}.type
        val currencyListingTypeToken = object : TypeToken<CurrencyListing>() {}.type

        val currencies = arrayListOf<Currency>()

        val dataJson = json!!.asJsonObject.get("data")
        for (data in dataJson.asJsonObject.entrySet()) {
            val currency = gson.fromJson(
                data.value,
                currencyTypeToken
            ) as Currency

            val currencyAsJson = gson.toJsonTree(data.value)

            val priceInfo = currencyAsJson.asJsonObject
                .get("quote").asJsonObject
                .get("USD")
            val currencyListing: CurrencyListing = gson.fromJson(
                priceInfo,
                currencyListingTypeToken
            )

            currency.listing = currencyListing
            val currencyType = CurrencyType.typeForCurrency(currency)
            if (currencyType != null) {
                currency.type = currencyType
                currencies.add(currency)
            }
        }
        return CurrencyResponse(
            currencies
        )
    }

}
