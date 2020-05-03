package com.smlnskgmail.jaman.cryptotracker.model.currency.cmc

import com.google.gson.JsonDeserializer
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcap.deserializers.CurrencyListingResponseDeserializer

class CurrencyListingDeserializerTest : BaseDeserializerTest() {

    override fun pathToJson(): Int {
        return com.smlnskgmail.jaman.cryptotracker.test.R.raw.test_currency_listing_response
    }

    override fun deserializer(): JsonDeserializer<*> {
        return CurrencyListingResponseDeserializer()
    }

}
