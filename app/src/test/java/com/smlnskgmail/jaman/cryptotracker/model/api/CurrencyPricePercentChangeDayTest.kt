package com.smlnskgmail.jaman.cryptotracker.model.api

import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyPricePercentChangeDay
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class CurrencyPricePercentChangeDayTest {

    @Test
    fun validateFields() {
        val value = 10f
        val currencyPricePercentChangeDay = CurrencyPricePercentChangeDay(
            value
        )

        assertEquals(
            value,
            currencyPricePercentChangeDay.value()
        )
        assertEquals(
            "10%",
            currencyPricePercentChangeDay.toString()
        )
    }

    @Test
    fun validateEquals() {
        val currencyPricePercentChangeDay = CurrencyPricePercentChangeDay(
            30f
        )
        assertEquals(
            currencyPricePercentChangeDay,
            currencyPricePercentChangeDay
        )

        assertEquals(
            CurrencyPricePercentChangeDay(
                10f
            ),
            CurrencyPricePercentChangeDay(
                10f
            )
        )
        assertNotEquals(
            CurrencyPricePercentChangeDay(
                5f
            ),
            CurrencyPricePercentChangeDay(
                15f
            )
        )
        assertNotEquals(
            "String",
            CurrencyPricePercentChangeDay(
                5f
            )
        )
    }

    @Test
    fun validateHashCode() {
        assertEquals(
            CurrencyPricePercentChangeDay(
                10f
            ).hashCode(),
            CurrencyPricePercentChangeDay(
                10f
            ).hashCode()
        )
        assertNotEquals(
            CurrencyPricePercentChangeDay(
                5f
            ).hashCode(),
            CurrencyPricePercentChangeDay(
                15f
            ).hashCode()
        )
    }

}
