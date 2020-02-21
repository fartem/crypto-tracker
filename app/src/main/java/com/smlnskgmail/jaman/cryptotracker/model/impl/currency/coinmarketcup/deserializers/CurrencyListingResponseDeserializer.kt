package com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcup.deserializers

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcup.CmcCurrencyListing
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcup.retrofit.responses.CurrencyListingResponse
import java.lang.reflect.Type

class CurrencyListingResponseDeserializer : JsonDeserializer<CurrencyListingResponse> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): CurrencyListingResponse {
        val gson = Gson()

        val currencyListingTypeToken = object : TypeToken<CmcCurrencyListing>() {}.type

        val dataJson = json!!.asJsonObject.get("data")
        val quoteUSD = dataJson.asJsonObject.entrySet().first()
            .value.asJsonObject
            .get("quote").asJsonObject
            .get("USD")
        val currencyListing: CmcCurrencyListing = gson.fromJson(
            quoteUSD,
            currencyListingTypeToken
        )
        return CurrencyListingResponse(
            currencyListing
        )
    }

}
