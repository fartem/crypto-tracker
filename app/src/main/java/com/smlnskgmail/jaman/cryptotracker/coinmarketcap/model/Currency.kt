package com.smlnskgmail.jaman.cryptotracker.coinmarketcap.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Currency(

    @SerializedName(
        "id"
    )
    val id: Int,

    @SerializedName(
        "name"
    )
    val name: String,

    @SerializedName(
        "symbol"
    )
    val symbol: String,

    @SerializedName(
        "slug"
    )
    val slug: String,

    @SerializedName(
        "date_added"
    )
    val firstHistoricalData: String,

    @SerializedName(
        "last_updated"
    )
    val lastHistoricalData: String,

    var listing: CurrencyListing

) : Serializable