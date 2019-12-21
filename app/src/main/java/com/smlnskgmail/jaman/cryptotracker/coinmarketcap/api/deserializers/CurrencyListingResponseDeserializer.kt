package com.smlnskgmail.jaman.cryptotracker.coinmarketcap.api.deserializers

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.api.responses.CurrencyListingResponse
import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.model.CurrencyListing
import java.lang.reflect.Type

class CurrencyListingResponseDeserializer : JsonDeserializer<CurrencyListingResponse> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): CurrencyListingResponse {
        val gson = Gson()

        val currencyListingType = object : TypeToken<CurrencyListing>() {}.type

        val dataJson = json!!.asJsonObject.get("data")
        val quoteUSD = dataJson.asJsonObject.entrySet().first()
            .value.asJsonObject.get("quote").asJsonObject
            .get("USD")
        val currencyListing: CurrencyListing = gson.fromJson(
            quoteUSD,
            currencyListingType
        )
        return CurrencyListingResponse(currencyListing)
    }

}
