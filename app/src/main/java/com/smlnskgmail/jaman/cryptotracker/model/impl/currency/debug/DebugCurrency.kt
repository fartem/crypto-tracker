package com.smlnskgmail.jaman.cryptotracker.model.impl.currency.debug

import com.smlnskgmail.jaman.cryptotracker.model.api.currency.*

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

    override fun firstHistoricalDate(): String {
        return firstHistoricalData
    }

    override fun lastHistoricalDate(): String {
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
                override fun currentPrice(): CurrencyPrice {
                    return CurrencyPrice(
                        1000.751f
                    )
                }

                override fun changeHour(): CurrencyPricePercentChangeDay {
                    return CurrencyPricePercentChangeDay(
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
        if (this === other) {
            return true
        }

        other as Currency

        if (name != other.name()) {
            return false
        }
        if (symbol() != other.symbol()) {
            return false
        }
        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + symbol().hashCode()
        return result
    }

}
