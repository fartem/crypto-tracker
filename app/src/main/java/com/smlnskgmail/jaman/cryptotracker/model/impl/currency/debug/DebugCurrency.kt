package com.smlnskgmail.jaman.cryptotracker.model.impl.currency.debug

import com.smlnskgmail.jaman.cryptotracker.model.api.currency.Currency
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyListing
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyPriceValue
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyType

// CPD-OFF
class DebugCurrency(

    private val name: String,
    private val symbol: String,
    private val slug: String,
    private val firstHistoricalData: String,
    private val lastHistoricalData: String,
    private val type: CurrencyType

) : Currency {

    private var currencyListing: CurrencyListing? = null

    override fun name(): String {
        return name
    }

    override fun symbol(): String {
        return symbol
    }

    override fun slug(): String {
        return slug
    }

    override fun firstHistoricalData(): String {
        return firstHistoricalData
    }

    override fun lastHistoricalData(): String {
        return lastHistoricalData
    }

    override fun updateCurrencyListing(
        currencyListing: CurrencyListing
    ) {
        this.currencyListing = currencyListing
    }

    @SuppressWarnings("MagicNumber")
    override fun currencyListing(): CurrencyListing {
        if (currencyListing == null) {
            currencyListing = object :
                CurrencyListing {
                override fun currentPrice(): CurrencyPriceValue {
                    return CurrencyPriceValue(
                        1000.751f
                    )
                }

                override fun changeHour(): CurrencyPriceValue {
                    return CurrencyPriceValue(
                        0.19f
                    )
                }
            }
        }
        return currencyListing!!
    }

    override fun currencyType(): CurrencyType {
        return type
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        other as Currency

        if (name != other.name()) return false
        if (symbol() != other.symbol()) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + symbol().hashCode()
        return result
    }

}
