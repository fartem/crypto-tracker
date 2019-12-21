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

) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Currency

        if (id != other.id) return false
        if (name != other.name) return false
        if (symbol != other.symbol) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + symbol.hashCode()
        return result
    }

}
