package com.smlnskgmail.jaman.cryptotracker.currencies.impl.cache.mapdb

import com.smlnskgmail.jaman.cryptotracker.currencies.api.Currency
import com.smlnskgmail.jaman.cryptotracker.currencies.api.cache.CurrencyInstanceProvider
import org.mapdb.Serializer
import java.io.DataInput
import java.io.DataOutput

class MapDbCurrencySerializer(
    private val currencyInstanceProvider: CurrencyInstanceProvider
) : Serializer<Currency>() {

    override fun serialize(
        out: DataOutput,
        value: Currency
    ) {
        currencyInstanceProvider.currencyTo(
            out,
            value
        )
    }

    override fun deserialize(
        input: DataInput,
        available: Int
    ): Currency {
        return currencyInstanceProvider.currencyFrom(input)
    }

}
