package com.smlnskgmail.jaman.cryptotracker.logic.currencies.impl.coinmarketcup

import com.google.gson.annotations.SerializedName
import com.smlnskgmail.jaman.cryptotracker.logic.currencies.api.CurrencyListing

data class CmcCurrencyListing(

    @SerializedName("price")
    private val price: Float,

    @SerializedName("percent_change_1h")
    private val changeHour: Float

) : CurrencyListing {

    companion object {

        const val serialVersionUID = 579L

    }

    override fun price(): Float {
        return price
    }

    override fun changeHour(): Float {
        return changeHour
    }

}
