package com.smlnskgmail.jaman.cryptotracker.model.impl.currency.debug

import com.smlnskgmail.jaman.cryptotracker.model.api.cache.CurrencyInstanceProvider
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.Currency
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyType
import java.io.DataInput
import java.io.DataOutput

// CPD-OFF
class DebugCurrencyMapDbInstanceProvider : CurrencyInstanceProvider {

    override fun currencyTo(
        writer: Any,
        currency: Currency
    ) {
        writer as DataOutput
        writer.writeUTF(currency.name())
        writer.writeUTF(currency.symbol())
        writer.writeUTF(currency.slug())
        writer.writeUTF(currency.firstHistoricalData())
        writer.writeUTF(currency.lastHistoricalData())
        writer.writeUTF(
            currency.currencyType().toString()
        )
    }

    override fun currencyFrom(
        reader: Any
    ): Currency {
        reader as DataInput
        return DebugCurrency(
            reader.readUTF(),
            reader.readUTF(),
            reader.readUTF(),
            reader.readUTF(),
            reader.readUTF(),
            CurrencyType.valueOf(
                reader.readUTF()
            )
        )
    }

}
