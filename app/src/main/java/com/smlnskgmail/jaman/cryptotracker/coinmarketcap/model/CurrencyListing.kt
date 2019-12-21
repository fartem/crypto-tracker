package com.smlnskgmail.jaman.cryptotracker.coinmarketcap.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CurrencyListing(

    @SerializedName("price")
    val price: Float,

    @SerializedName("percent_change_1h")
    val chengeHour: Float

) : Serializable {

    companion object {

        const val serialVersionUID = 579L

    }

}
