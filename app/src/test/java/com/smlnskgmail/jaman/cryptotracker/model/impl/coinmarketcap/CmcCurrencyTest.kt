package com.smlnskgmail.jaman.cryptotracker.model.impl.coinmarketcap

import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyPrice
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyPricePercentChangeDay
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyType
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcap.CmcCurrency
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcap.CmcCurrencyListing
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class CmcCurrencyTest {

    @Test
    fun validateFields() {
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
        val currency = CmcCurrency(
            id,
            name,
            symbol,
            slug,
            firstHistoricalDate,
            lastHistoricalDate,
            currencyListing,
            currencyType
        )

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
            currencyType,
            currency.currencyType
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
        val firstCurrency = CmcCurrency(
            id,
            name,
            symbol,
            slug,
            firstHistoricalDate,
            lastHistoricalDate,
            currencyListing,
            currencyType
        )
        assertEquals(
            firstCurrency,
            firstCurrency
        )

        val secondCurrency = CmcCurrency(
            id,
            name,
            symbol,
            slug,
            firstHistoricalDate,
            lastHistoricalDate,
            currencyListing,
            currencyType
        )
        assertEquals(
            firstCurrency,
            secondCurrency
        )

        val thirdCurrency = CmcCurrency(
            id,
            "AE",
            "AE",
            "AE",
            firstHistoricalDate,
            lastHistoricalDate,
            currencyListing,
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
        val firstCurrency = CmcCurrency(
            id,
            name,
            symbol,
            slug,
            firstHistoricalDate,
            lastHistoricalDate,
            currencyListing,
            currencyType
        )
        assertEquals(
            firstCurrency.hashCode(),
            firstCurrency.hashCode()
        )

        val secondCurrency = CmcCurrency(
            id,
            name,
            symbol,
            slug,
            firstHistoricalDate,
            lastHistoricalDate,
            currencyListing,
            currencyType
        )
        assertEquals(
            firstCurrency.hashCode(),
            secondCurrency.hashCode()
        )

        val thirdCurrency = CmcCurrency(
            id,
            "AE",
            "AE",
            "AE",
            firstHistoricalDate,
            lastHistoricalDate,
            currencyListing,
            CurrencyType.AE
        )
        assertNotEquals(
            secondCurrency.hashCode(),
            thirdCurrency.hashCode()
        )
    }

}
