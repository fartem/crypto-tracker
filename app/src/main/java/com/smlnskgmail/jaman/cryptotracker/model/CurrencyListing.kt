package com.smlnskgmail.jaman.cryptotracker.model

import com.google.gson.annotations.SerializedName

class CurrencyListing(

    @SerializedName(
        "price"
    )
    val price: Float,

    @SerializedName(
        "percent_change_1h"
    )
    val chengeHour: Float,

    @SerializedName(
        "last_updated"
    )
    val latestUpdated: String


)