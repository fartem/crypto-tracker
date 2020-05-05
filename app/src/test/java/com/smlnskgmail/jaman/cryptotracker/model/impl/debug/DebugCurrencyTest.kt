package com.smlnskgmail.jaman.cryptotracker.model.impl.debug

import com.smlnskgmail.jaman.cryptotracker.model.BaseEntityTest
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyType
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.debug.DebugCurrency
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals

class DebugCurrencyTest : BaseEntityTest() {

    private val name = "BTC"
    private val symbol = "BTC"
    private val slug = "BTC"
    private val firstHistoricalDate = "-"
    private val lastHistoricalDate = "-"
    private val currencyType = CurrencyType.BTC

    private val currency = DebugCurrency(
        name,
        symbol,
        slug,
        firstHistoricalDate,
        lastHistoricalDate,
        currencyType
    )

    override fun `Validate fields`() {
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
            currency.currencyListing(),
            currency.currencyListing()
        )
        assertEquals(
            currencyType,
            currency.currencyType()
        )
    }

    override fun `Validate equals()`() {
        assertEquals(
            currency,
            currency
        )
        assertEquals(
            DebugCurrency(
                name,
                symbol,
                slug,
                firstHistoricalDate,
                lastHistoricalDate,
                currencyType
            ),
            currency
        )

        assertNotEquals(
            DebugCurrency(
                "AE",
                symbol,
                slug,
                firstHistoricalDate,
                lastHistoricalDate,
                currencyType
            ),
            currency
        )
        assertNotEquals(
            DebugCurrency(
                name,
                "AE",
                slug,
                firstHistoricalDate,
                lastHistoricalDate,
                currencyType
            ),
            currency
        )
//        assertNotEquals(
//            currency,
//            null
//        )
//        assertNotEquals(
//            currency,
//            "String"
//        )
    }

    override fun `Validate hashCode()`() {
        assertEquals(
            currency.hashCode(),
            currency.hashCode()
        )
        assertEquals(
            DebugCurrency(
                name,
                symbol,
                slug,
                firstHistoricalDate,
                lastHistoricalDate,
                currencyType
            ).hashCode(),
            currency.hashCode()
        )

        assertNotEquals(
            DebugCurrency(
                "AE",
                "AE",
                "AE",
                firstHistoricalDate,
                lastHistoricalDate,
                CurrencyType.AE
            ).hashCode(),
            currency.hashCode()
        )
    }

}
