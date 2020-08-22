package com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcap

import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyListing
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyPrice
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyPricePercentChangeDay

data class CmcCurrencyListing(
    private val currentPrice: CurrencyPrice,
    private val changeHourPrice: CurrencyPricePercentChangeDay
) : CurrencyListing {

    companion object {

        @Suppress("unused")
        const val serialVersionUID = 579L

    }

    override fun currentPrice(): CurrencyPrice {
        return currentPrice
    }

    override fun changeHour(): CurrencyPricePercentChangeDay {
        return changeHourPrice
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (javaClass != other?.javaClass) {
            return false
        }

        other as CmcCurrencyListing

        if (currentPrice != other.currentPrice) {
            return false
        }
        if (changeHourPrice != other.changeHourPrice) {
            return false
        }
        return true
    }

    override fun hashCode(): Int {
        var result = currentPrice.hashCode()
        result = 31 * result + changeHourPrice.hashCode()
        return result
    }

}
