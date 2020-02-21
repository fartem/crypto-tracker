package com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcup

import com.google.gson.annotations.SerializedName
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.Currency
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyListing
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyType

class CmcCurrency(

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    private val name: String,

    @SerializedName("symbol")
    private val symbol: String,

    @SerializedName("slug")
    private val slug: String,

    @SerializedName("date_added")
    private val firstHistoricalData: String,

    @SerializedName("last_updated")
    private val lastHistoricalData: String,

    var currencyListing: CurrencyListing,

    var currencyType: CurrencyType

) : Currency {

    companion object {

        const val serialVersionUID = 153L

    }

    override fun name(): String {
        return name
    }

    override fun symbol(): String {
        return symbol
    }

    override fun slug(): String {
        return slug
    }

    override fun firstHistoricalData(): String {
        return firstHistoricalData
    }

    override fun lastHistoricalData(): String {
        return lastHistoricalData
    }

    override fun updateCurrencyListing(
        currencyListing: CurrencyListing
    ) {
        this.currencyListing = currencyListing
    }

    override fun currencyListing(): CurrencyListing {
        return currencyListing
    }

    override fun currencyType(): CurrencyType {
        return currencyType
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Currency

        if (name != other.name()) return false
        if (symbol != other.symbol()) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + symbol.hashCode()
        return result
    }

}
