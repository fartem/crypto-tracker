package com.smlnskgmail.jaman.cryptotracker.model.impl.cache.mapdb

import com.smlnskgmail.jaman.cryptotracker.model.api.cache.CurrencyInstanceProvider
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.Currency
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
