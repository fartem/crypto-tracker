package com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcup.cache

import com.smlnskgmail.jaman.cryptotracker.model.api.cache.CurrencyInstanceProvider
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.Currency
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyPrice
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyPricePercentChangeDay
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyType
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcup.CmcCurrency
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcup.CmcCurrencyListing
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
                CurrencyPrice(
                    reader.readFloat()
                ),
                CurrencyPricePercentChangeDay(
                    reader.readFloat()
                )
            ),
            CurrencyType.valueOf(
                reader.readUTF()
            )
        )
    }

}
