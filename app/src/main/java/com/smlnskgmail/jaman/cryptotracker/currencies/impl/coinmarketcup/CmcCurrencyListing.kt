package com.smlnskgmail.jaman.cryptotracker.currencies.impl.coinmarketcup

import com.google.gson.annotations.SerializedName
import com.smlnskgmail.jaman.cryptotracker.currencies.api.CurrencyListing
import com.smlnskgmail.jaman.cryptotracker.currencies.api.CurrencyPriceValue

data class CmcCurrencyListing(

    @SerializedName("price")
    private val price: Float,

    @SerializedName("percent_change_1h")
    private val changeHour: Float

) : CurrencyListing {

    companion object {

        @Suppress("unused")
        const val serialVersionUID = 579L

    }

    private val currentPrice: CurrencyPriceValue = CurrencyPriceValue(price)
    private val changeHourPrice: CurrencyPriceValue = CurrencyPriceValue(changeHour)

    override fun currentPrice(): CurrencyPriceValue {
        return currentPrice
    }

    override fun changeHour(): CurrencyPriceValue {
        return changeHourPrice
    }

}
