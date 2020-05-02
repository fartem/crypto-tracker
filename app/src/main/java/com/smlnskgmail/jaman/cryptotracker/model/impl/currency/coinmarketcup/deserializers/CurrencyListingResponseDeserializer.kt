package com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcup.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyPricePercentChangeDay
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyPrice
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcup.CmcCurrencyListing
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcup.retrofit.responses.CurrencyListingResponse
import java.lang.reflect.Type

class CurrencyListingResponseDeserializer : JsonDeserializer<CurrencyListingResponse> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): CurrencyListingResponse {
        val dataJson = json!!.asJsonObject.get("data")
        val quoteUSD = dataJson.asJsonObject.entrySet().first()
            .value.asJsonObject
            .get("quote").asJsonObject
            .get("USD") as JsonObject

        val currencyListing = CmcCurrencyListing(
            CurrencyPrice(
                quoteUSD.get("price").asFloat
            ),
            CurrencyPricePercentChangeDay(
                quoteUSD.get("percent_change_24h").asFloat
            )
        )
        return CurrencyListingResponse(
            currencyListing
        )
    }

}
