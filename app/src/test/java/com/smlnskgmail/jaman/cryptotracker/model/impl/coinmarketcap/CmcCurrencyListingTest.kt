package com.smlnskgmail.jaman.cryptotracker.model.impl.coinmarketcap

import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyPrice
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyPricePercentChangeDay
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcap.CmcCurrencyListing
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class CmcCurrencyListingTest {

    @Test
    fun validateFields() {
        val price = CurrencyPrice(
            1_000f
        )
        val percentChange = CurrencyPricePercentChangeDay(
            10f
        )
        val currencyListing = CmcCurrencyListing(
            price,
            percentChange
        )

        assertEquals(
            price,
            currencyListing.currentPrice()
        )
        assertEquals(
            percentChange,
            currencyListing.changeDay()
        )
    }

    @Test
    fun validateEquals() {
        val price = CurrencyPrice(
            1_000f
        )
        val percentChange = CurrencyPricePercentChangeDay(
            10f
        )
        val currencyListing = CmcCurrencyListing(
            price,
            percentChange
        )

        assertEquals(
            currencyListing,
            currencyListing
        )

        assertEquals(
            CmcCurrencyListing(
                price,
                percentChange
            ),
            currencyListing
        )
        assertNotEquals(
            CmcCurrencyListing(
                CurrencyPrice(
                    30f
                ),
                percentChange
            ),
            currencyListing
        )
        assertNotEquals(
            CmcCurrencyListing(
                price,
                CurrencyPricePercentChangeDay(
                    30f
                )
            ),
            currencyListing
        )

        assertNotEquals(
            currencyListing,
            "String"
        )
        assertNotEquals(
            currencyListing,
            null
        )
    }

    @Test
    fun validateHashCode() {
        val price = CurrencyPrice(
            1_000f
        )
        val percentChange = CurrencyPricePercentChangeDay(
            10f
        )
        val currencyListing = CmcCurrencyListing(
            price,
            percentChange
        )

        assertEquals(
            CmcCurrencyListing(
                price,
                percentChange
            ).hashCode(),
            currencyListing.hashCode()
        )
        assertNotEquals(
            CmcCurrencyListing(
                CurrencyPrice(
                    30f
                ),
                percentChange
            ).hashCode(),
            currencyListing.hashCode()
        )
    }

}
