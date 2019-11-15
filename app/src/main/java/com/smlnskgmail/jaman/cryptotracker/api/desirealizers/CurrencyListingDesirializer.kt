package com.smlnskgmail.jaman.cryptotracker.api.desirealizers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.smlnskgmail.jaman.cryptotracker.model.CurrencyListing
import java.lang.reflect.Type

class CurrencyListingDesirializer : JsonDeserializer<CurrencyListing> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): CurrencyListing {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}