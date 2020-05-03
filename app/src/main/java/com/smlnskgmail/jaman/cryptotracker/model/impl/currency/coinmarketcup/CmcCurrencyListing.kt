package com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcup

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

}
