package com.smlnskgmail.jaman.cryptotracker.model

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
        "first_historical_data"
    )
    val firstHistoricalData: String,

    @SerializedName(
        "last_historical_data"
    )
    val lastHistoricalData: String

) : Serializable