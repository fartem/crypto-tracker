package com.smlnskgmail.jaman.cryptotracker.api.deserializers

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import com.smlnskgmail.jaman.cryptotracker.api.ApiResponseStatus
import com.smlnskgmail.jaman.cryptotracker.api.responses.CurrencyResponse
import com.smlnskgmail.jaman.cryptotracker.model.Currency
import com.smlnskgmail.jaman.cryptotracker.model.CurrencyListing
import java.lang.reflect.Type

class CurrencyResponseDeserializer : JsonDeserializer<CurrencyResponse> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): CurrencyResponse {
        val gson = Gson()

        val currencyType = object : TypeToken<Currency>() {}.type
        val currencyListingType = object : TypeToken<CurrencyListing>() {}.type
        val apiResponseStatusType = object : TypeToken<ApiResponseStatus>() {}.type

        val currencies = arrayListOf<Currency>()

        val dataJson = json!!.asJsonObject.get(
            "data"
        )
        for (data in dataJson.asJsonObject.entrySet()) {
            val currency = gson.fromJson(
                data.value,
                currencyType
            ) as Currency

            val currencyAsJson = gson.toJsonTree(data.value)

            val priceInfo = currencyAsJson.asJsonObject.get(
                "quote"
            ).asJsonObject.get(
                "USD"
            )
            val currencyListing: CurrencyListing = gson.fromJson(
                priceInfo,
                currencyListingType
            )

            currency.listing = currencyListing

            currencies.add(
                currency
            )
        }

        val apiResponseStatusData = json.asJsonObject.get(
            "status"
        ).toString()
        val apiStatus: ApiResponseStatus = gson.fromJson(
            apiResponseStatusData,
            apiResponseStatusType
        )

        return CurrencyResponse(
            currencies,
            apiStatus
        )
    }

}