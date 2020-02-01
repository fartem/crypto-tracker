package com.smlnskgmail.jaman.cryptotracker.parsing

import com.google.gson.JsonDeserializer
import com.smlnskgmail.jaman.cryptotracker.logic.api.impl.coinmarketcup.deserializers.CurrencyResponseDeserializer

class CurrencyDeserializerTest : BaseDeserializerTest() {

    override fun pathToJson(): Int {
        return com.smlnskgmail.jaman.cryptotracker.test.R.raw.test_currency_response
    }

    override fun deserializer(): JsonDeserializer<*> {
        return CurrencyResponseDeserializer()
    }

}
