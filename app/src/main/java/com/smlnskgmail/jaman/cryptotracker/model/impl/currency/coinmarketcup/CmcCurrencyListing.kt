package com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcup

import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyListing
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyPriceValue

data class CmcCurrencyListing(
    private val currentPrice: CurrencyPriceValue,
    private val changeHourPrice: CurrencyPriceValue
) : CurrencyListing {

    companion object {

        @Suppress("unused")
        const val serialVersionUID = 579L

    }

    override fun currentPrice(): CurrencyPriceValue {
        return currentPrice
    }

    override fun changeHour(): CurrencyPriceValue {
        return changeHourPrice
    }

}
