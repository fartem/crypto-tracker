package com.smlnskgmail.jaman.cryptotracker.model.currency.cmc

import com.google.gson.JsonDeserializer
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcup.deserializers.CurrencyResponseDeserializer

class CurrencyDeserializerTest : BaseDeserializerTest() {

    override fun pathToJson(): Int {
        return com.smlnskgmail.jaman.cryptotracker.test.R.raw.test_currency_response
    }

    override fun deserializer(): JsonDeserializer<*> {
        return CurrencyResponseDeserializer()
    }

}
