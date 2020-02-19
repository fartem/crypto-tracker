package com.smlnskgmail.jaman.cryptotracker.currencies.impl.coinmarketcup.cache

import com.smlnskgmail.jaman.cryptotracker.currencies.api.Currency
import com.smlnskgmail.jaman.cryptotracker.currencies.api.CurrencyType
import com.smlnskgmail.jaman.cryptotracker.currencies.api.cache.CurrencyInstanceProvider
import com.smlnskgmail.jaman.cryptotracker.currencies.impl.coinmarketcup.CmcCurrency
import com.smlnskgmail.jaman.cryptotracker.currencies.impl.coinmarketcup.CmcCurrencyListing
import java.io.DataInput
import java.io.DataOutput

class CmcCurrencyMapDbInstanceProvider : CurrencyInstanceProvider {

    override fun currencyTo(
        writer: Any,
        currency: Currency
    ) {
        currency as CmcCurrency
        writer as DataOutput
        writer.writeInt(currency.id)
        writer.writeUTF(currency.name())
        writer.writeUTF(currency.symbol())
        writer.writeUTF(currency.slug())
        writer.writeUTF(currency.firstHistoricalData())
        writer.writeUTF(currency.lastHistoricalData())
        writer.writeFloat(
            currency.currencyListing().currentPrice().value()
        )
        writer.writeFloat(
            currency.currencyListing().changeHour().value()
        )
        writer.writeUTF(
            currency.currencyType().toString()
        )
    }

    override fun currencyFrom(
        reader: Any
    ): Currency {
        reader as DataInput
        return CmcCurrency(
            reader.readInt(),
            reader.readUTF(),
            reader.readUTF(),
            reader.readUTF(),
            reader.readUTF(),
            reader.readUTF(),
            CmcCurrencyListing(
                reader.readFloat(),
                reader.readFloat()
            ),
            CurrencyType.valueOf(
                reader.readUTF()
            )
        )
    }

}
