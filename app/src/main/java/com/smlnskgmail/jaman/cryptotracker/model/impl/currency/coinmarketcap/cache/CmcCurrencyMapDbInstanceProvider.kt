package com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcap.cache

import com.smlnskgmail.jaman.cryptotracker.model.api.cache.CurrencyInstanceProvider
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.Currency
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyPrice
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyPricePercentChangeDay
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyType
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcap.CmcCurrency
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcap.CmcCurrencyListing
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
        writer.writeUTF(currency.firstHistoricalDate())
        writer.writeUTF(currency.lastHistoricalDate())
        writer.writeFloat(
            currency.currencyListing().currentPrice().price
        )
        writer.writeFloat(
            currency.currencyListing().changeHour().value
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
