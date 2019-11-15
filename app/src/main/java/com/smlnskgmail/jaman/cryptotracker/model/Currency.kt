package com.smlnskgmail.jaman.cryptotracker.model

import com.google.gson.annotations.SerializedName

class Currency(

    @SerializedName(
        "name"
    )
    val name: String,

    @SerializedName(
        "symbol"
    )
    val symbol: String,

    @SerializedName(
        "rank"
    )
    val rank: String,

    @SerializedName(
        "price_usd"
    )
    val priceInUSD: String,

    @SerializedName(
        "price_btc"
    )
    val priceInBTC: String,

    @SerializedName(
        "last_updated"
    )
    var lastUpdatedAt: String

)