package com.smlnskgmail.jaman.cryptotracker.model.impl.debug

import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyPrice
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyPricePercentChangeDay
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyType
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcap.CmcCurrencyListing
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.debug.DebugCurrency
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class DebugCurrencyTest {

    @Test
    fun validateFields() {
        val name = "BTC"
        val symbol = "BTC"
        val slug = "BTC"
        val firstHistoricalDate = "-"
        val lastHistoricalDate = "-"
        val currencyType = CurrencyType.BTC
        val currency = DebugCurrency(
            name,
            symbol,
            slug,
            firstHistoricalDate,
            lastHistoricalDate,
            currencyType
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
            currency.currencyListing(),
            currency.currencyListing()
        )
        assertEquals(
            currencyType,
            currency.currencyType()
        )
    }

    @Test
    fun validateEquals() {
        val id = 1
        val name = "BTC"
        val symbol = "BTC"
        val slug = "BTC"
        val firstHistoricalDate = "-"
        val lastHistoricalDate = "-"
        val currencyListing = CmcCurrencyListing(
            CurrencyPrice(
                8_000f
            ),
            CurrencyPricePercentChangeDay(
                3f
            )
        )
        val currencyType = CurrencyType.BTC
        val firstCurrency = DebugCurrency(
            name,
            symbol,
            slug,
            firstHistoricalDate,
            lastHistoricalDate,
            currencyType
        )
        assertEquals(
            firstCurrency,
            firstCurrency
        )

        val secondCurrency = DebugCurrency(
            name,
            symbol,
            slug,
            firstHistoricalDate,
            lastHistoricalDate,
            currencyType
        )
        assertEquals(
            firstCurrency,
            secondCurrency
        )

        val thirdCurrency = DebugCurrency(
            "AE",
            "AE",
            "AE",
            firstHistoricalDate,
            lastHistoricalDate,
            CurrencyType.AE
        )
        assertNotEquals(
            secondCurrency,
            thirdCurrency
        )
    }

    @Test
    fun validateHashCode() {
        val id = 1
        val name = "BTC"
        val symbol = "BTC"
        val slug = "BTC"
        val firstHistoricalDate = "-"
        val lastHistoricalDate = "-"
        val currencyListing = CmcCurrencyListing(
            CurrencyPrice(
                8_000f
            ),
            CurrencyPricePercentChangeDay(
                3f
            )
        )
        val currencyType = CurrencyType.BTC
        val firstCurrency = DebugCurrency(
            name,
            symbol,
            slug,
            firstHistoricalDate,
            lastHistoricalDate,
            currencyType
        )
        assertEquals(
            firstCurrency.hashCode(),
            firstCurrency.hashCode()
        )

        val secondCurrency = DebugCurrency(
            name,
            symbol,
            slug,
            firstHistoricalDate,
            lastHistoricalDate,
            currencyType
        )
        assertEquals(
            firstCurrency.hashCode(),
            secondCurrency.hashCode()
        )

        val thirdCurrency = DebugCurrency(
            "AE",
            "AE",
            "AE",
            firstHistoricalDate,
            lastHistoricalDate,
            CurrencyType.AE
        )
        assertNotEquals(
            secondCurrency.hashCode(),
            thirdCurrency.hashCode()
        )
    }

}
