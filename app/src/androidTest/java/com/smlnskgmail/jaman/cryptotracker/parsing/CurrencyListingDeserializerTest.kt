package com.smlnskgmail.jaman.cryptotracker.parsing

import com.google.gson.JsonDeserializer
import com.smlnskgmail.jaman.cryptotracker.logic.currencies.impl.coinmarketcup.deserializers.CurrencyListingResponseDeserializer

class CurrencyListingDeserializerTest : BaseDeserializerTest() {

    override fun pathToJson(): Int {
        return com.smlnskgmail.jaman.cryptotracker.test.R.raw.test_currency_listing_response
    }

    override fun deserializer(): JsonDeserializer<*> {
        return CurrencyListingResponseDeserializer()
    }

}
