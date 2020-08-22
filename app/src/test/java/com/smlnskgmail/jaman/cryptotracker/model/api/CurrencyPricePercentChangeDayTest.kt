package com.smlnskgmail.jaman.cryptotracker.model.api

import com.smlnskgmail.jaman.cryptotracker.model.BaseEntityTest
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyPricePercentChangeDay
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals

class CurrencyPricePercentChangeDayTest : BaseEntityTest() {

    private val value = 10f
    private val currencyPricePercentChangeDay = CurrencyPricePercentChangeDay(
        value
    )

    override fun `Validate fields`() {
        assertEquals(
            value,
            currencyPricePercentChangeDay.value
        )
        assertEquals(
            "10%",
            currencyPricePercentChangeDay.toString()
        )
    }

    override fun `Validate equals()`() {
        assertEquals(
            currencyPricePercentChangeDay,
            currencyPricePercentChangeDay
        )

        assertEquals(
            CurrencyPricePercentChangeDay(
                value
            ),
            currencyPricePercentChangeDay
        )
        assertNotEquals(
            CurrencyPricePercentChangeDay(
                5f
            ),
            currencyPricePercentChangeDay
        )
        assertNotEquals(
            currencyPricePercentChangeDay,
            null
        )
        assertNotEquals(
            currencyPricePercentChangeDay,
            "String"
        )
    }

    override fun `Validate hashCode()`() {
        assertEquals(
            currencyPricePercentChangeDay.hashCode(),
            currencyPricePercentChangeDay.hashCode()
        )
        assertEquals(
            CurrencyPricePercentChangeDay(
                10f
            ).hashCode(),
            currencyPricePercentChangeDay.hashCode()
        )

        assertNotEquals(
            CurrencyPricePercentChangeDay(
                5f
            ).hashCode(),
            currencyPricePercentChangeDay.hashCode()
        )
    }

}
