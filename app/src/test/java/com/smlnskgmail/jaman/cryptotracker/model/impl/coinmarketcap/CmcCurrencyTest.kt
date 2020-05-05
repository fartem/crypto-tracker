package com.smlnskgmail.jaman.cryptotracker.model.impl.coinmarketcap

import com.smlnskgmail.jaman.cryptotracker.model.BaseEntityTest
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyPrice
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyPricePercentChangeDay
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyType
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcap.CmcCurrency
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcap.CmcCurrencyListing
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals

class CmcCurrencyTest : BaseEntityTest() {

    private val id = 1
    private val name = "BTC"
    private val symbol = "BTC"
    private val slug = "BTC"
    private val firstHistoricalDate = "-"
    private val lastHistoricalDate = "-"
    private val currencyListing = CmcCurrencyListing(
        CurrencyPrice(
            8_000f
        ),
        CurrencyPricePercentChangeDay(
            3f
        )
    )
    private val currencyType = CurrencyType.BTC

    private val currency = CmcCurrency(
        id,
        name,
        symbol,
        slug,
        firstHistoricalDate,
        lastHistoricalDate,
        currencyListing,
        currencyType
    )

    override fun `Validate fields`() {
        assertEquals(
            id,
            currency.id
        )
        assertEquals(
            name,
            currency.name()
        )
        assertEquals(
            symbol,
            currency.symbol()
        )
        assertEquals(
            slug,
            currency.slug()
        )
        assertEquals(
            firstHistoricalDate,
            currency.firstHistoricalDate()
        )
        assertEquals(
            lastHistoricalDate,
            currency.lastHistoricalDate()
        )
        assertEquals(
            currencyListing,
            currency.currencyListing
        )
        assertEquals(
            currencyListing,
            currency.currencyListing()
        )
        assertEquals(
            currencyType,
            currency.currencyType
        )
        assertEquals(
            currencyType,
            currency.currencyType()
        )

        val updatedCurrencyListing = CmcCurrencyListing(
            CurrencyPrice(
                190f
            ),
            CurrencyPricePercentChangeDay(
                11f
            )
        )
        currency.updateCurrencyListing(
            updatedCurrencyListing
        )
        assertEquals(
            updatedCurrencyListing,
            currency.currencyListing
        )
    }

    override fun `Validate equals()`() {
        assertEquals(
            currency,
            currency
        )
        assertEquals(
            CmcCurrency(
                id,
                name,
                symbol,
                slug,
                firstHistoricalDate,
                lastHistoricalDate,
                currencyListing,
                currencyType
            ),
            currency
        )

        assertNotEquals(
            CmcCurrency(
                id,
                "AE",
                symbol,
                slug,
                firstHistoricalDate,
                lastHistoricalDate,
                currencyListing,
                currencyType
            ),
            currency
        )
        assertNotEquals(
            CmcCurrency(
                id,
                name,
                "AE",
                slug,
                firstHistoricalDate,
                lastHistoricalDate,
                currencyListing,
                currencyType
            ),
            currency
        )
        assertNotEquals(
            currency,
            null
        )
        assertNotEquals(
            currency,
            "String"
        )
    }

    override fun `Validate hashCode()`() {
        assertEquals(
            currency.hashCode(),
            currency.hashCode()
        )
        assertEquals(
            CmcCurrency(
                id,
                name,
                symbol,
                slug,
                firstHistoricalDate,
                lastHistoricalDate,
                currencyListing,
                currencyType
            ).hashCode(),
            currency.hashCode()
        )

        assertNotEquals(
            CmcCurrency(
                id,
                "AE",
                "AE",
                "AE",
                firstHistoricalDate,
                lastHistoricalDate,
                currencyListing,
                CurrencyType.AE
            ).hashCode(),
            currency.hashCode()
        )
    }

}
