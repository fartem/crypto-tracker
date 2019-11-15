package com.smlnskgmail.jaman.cryptotracker.api.desirealizers

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import com.smlnskgmail.jaman.cryptotracker.api.ApiResponseStatus
import com.smlnskgmail.jaman.cryptotracker.api.responses.CurrencyResponse
import com.smlnskgmail.jaman.cryptotracker.model.Currency
import java.lang.reflect.Type

class CurrencyResponseDesirealizer : JsonDeserializer<CurrencyResponse> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): CurrencyResponse {
        val gson = Gson()

        val currencyType = object : TypeToken<Currency>() {}.type
        val apiResponseStatusType = object : TypeToken<ApiResponseStatus>() {}.type

        val currencies = arrayListOf<Currency>()

        val dataJson = json!!.asJsonObject.get(
            "data"
        )
        for (data in dataJson.asJsonArray) {
            val currencyAsString = data.toString()
            currencies.add(
                gson.fromJson(
                    currencyAsString,
                    currencyType
                )
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