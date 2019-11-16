package com.smlnskgmail.jaman.cryptotracker.api.cache

import com.smlnskgmail.jaman.cryptotracker.model.Currency
import com.smlnskgmail.jaman.cryptotracker.model.CurrencyListing

object CurrencyListingCache {

    private val currencyListings = hashMapOf<Currency, CurrencyListing>()

    fun putFor(currency: Currency, currencyListing: CurrencyListing) {
        currencyListings[currency] = currencyListing
    }

    fun loadFor(currency: Currency): CurrencyListing? {
        return currencyListings[currency]
    }

}