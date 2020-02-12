package com.smlnskgmail.jaman.cryptotracker.currencies.impl.coinmarketcup.cache

import com.smlnskgmail.jaman.cryptotracker.currencies.api.Currency
import com.smlnskgmail.jaman.cryptotracker.currencies.api.CurrencyType
import com.smlnskgmail.jaman.cryptotracker.currencies.impl.coinmarketcup.CmcCurrency
import com.smlnskgmail.jaman.cryptotracker.currencies.impl.coinmarketcup.CmcCurrencyListing
import org.mapdb.Serializer
import java.io.DataInput
import java.io.DataOutput

class CmcCurrencySerializer : Serializer<Currency>() {

    override fun serialize(out: DataOutput, value: Currency) {
        out.writeInt(value.id())
        out.writeUTF(value.name())
        out.writeUTF(value.symbol())
        out.writeUTF(value.slug())
        out.writeUTF(value.firstHistoricalData())
        out.writeUTF(value.lastHistoricalData())
        out.writeFloat(
            value.currencyListing().currentPrice().value()
        )
        out.writeFloat(
            value.currencyListing().changeHour().value()
        )
        out.writeUTF(
            value.currencyType().toString()
        )
    }

    override fun deserialize(input: DataInput, available: Int): Currency {
        return CmcCurrency(
            input.readInt(),
            input.readUTF(),
            input.readUTF(),
            input.readUTF(),
            input.readUTF(),
            input.readUTF(),
            CmcCurrencyListing(
                input.readFloat(),
                input.readFloat()
            ),
            CurrencyType.valueOf(
                input.readUTF()
            )
        )
    }

}
