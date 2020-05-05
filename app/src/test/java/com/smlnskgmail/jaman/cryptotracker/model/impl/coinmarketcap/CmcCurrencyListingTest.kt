package com.smlnskgmail.jaman.cryptotracker.model.impl.coinmarketcap

import com.smlnskgmail.jaman.cryptotracker.model.BaseEntityTest
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyPrice
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyPricePercentChangeDay
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcap.CmcCurrencyListing
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals

class CmcCurrencyListingTest : BaseEntityTest() {

    private val price = CurrencyPrice(
        1_000f
    )
    private val percentChange = CurrencyPricePercentChangeDay(
        10f
    )
    private val currencyListing = CmcCurrencyListing(
        price,
        percentChange
    )

    override fun `Validate fields`() {
        assertEquals(
            price,
            currencyListing.currentPrice()
        )
        assertEquals(
            percentChange,
            currencyListing.changeDay()
        )
    }

    override fun `Validate equals()`() {
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

    override fun `Validate hashCode()`() {
        assertEquals(
            currencyListing.hashCode(),
            currencyListing.hashCode()
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
