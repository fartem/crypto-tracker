package com.smlnskgmail.jaman.cryptotracker.coinmarketcap.api

import com.google.gson.annotations.SerializedName

class ApiResponseStatus(

    @SerializedName(
        "error_code"
    )
    val errorCode: Int,

    @SerializedName(
        "error_message"
    )
    val errorMessage: String

)