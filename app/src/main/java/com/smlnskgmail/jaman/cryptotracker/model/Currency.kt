package com.smlnskgmail.jaman.cryptotracker.model

import com.google.gson.annotations.SerializedName

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
    val symbol: String

)