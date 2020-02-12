package com.smlnskgmail.jaman.cryptotracker.currencies.impl.coinmarketcup

import com.google.gson.annotations.SerializedName
import com.smlnskgmail.jaman.cryptotracker.currencies.api.Currency
import com.smlnskgmail.jaman.cryptotracker.currencies.api.CurrencyListing
import com.smlnskgmail.jaman.cryptotracker.currencies.api.CurrencyType

class CmcCurrency(

    @SerializedName("id")
    private val id: Int,

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

    override fun id(): Int {
        return id
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

        if (id != other.id()) return false
        if (name != other.name()) return false
        if (symbol != other.symbol()) return false

        return true
    }

    override fun compareTo(other: Currency): Int {
        return id.compareTo(other.id())
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + symbol.hashCode()
        return result
    }

}
