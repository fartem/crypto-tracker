package com.smlnskgmail.jaman.cryptotracker.api

import com.google.gson.annotations.SerializedName

class ApiResponseStatus(

    @SerializedName(
        "timestamp"
    )
    val timestamp: String,

    @SerializedName(
        "error_code"
    )
    val errorCode: Int,

    @SerializedName(
        "error_message"
    )
    val errorMessage: String

)