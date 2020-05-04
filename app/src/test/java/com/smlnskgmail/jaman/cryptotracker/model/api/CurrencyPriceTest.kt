package com.smlnskgmail.jaman.cryptotracker.model.api

import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyPrice
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class CurrencyPriceTest {

    @Test
    fun validateFields() {
        val value = 8_000.5f
        val currencyPrice = CurrencyPrice(
            value
        )

        assertEquals(
            value,
            currencyPrice.value()
        )
        assertEquals(
            "$8,000.50",
            currencyPrice.toString()
        )
    }

    @Test
    fun validateEquals() {
        val currencyPrice = CurrencyPrice(
            7_000f
        )
        assertEquals(
            currencyPrice,
            currencyPrice
        )

        assertEquals(
            CurrencyPrice(
                1_000f
            ),
            CurrencyPrice(
                1_000f
            )
        )
        assertNotEquals(
            CurrencyPrice(
                3_000f
            ),
            CurrencyPrice(
                5_000f
            )
        )
        assertNotEquals(
            CurrencyPrice(
                10f
            ),
            null
        )
        assertNotEquals(
            CurrencyPrice(
                900f
            ),
            "String"
        )
    }

    @Test
    fun validateHashCode() {
        assertEquals(
            CurrencyPrice(
                1_000f
            ).hashCode(),
            CurrencyPrice(
                1_000f
            ).hashCode()
        )
        assertNotEquals(
            CurrencyPrice(
                3_000f
            ).hashCode(),
            CurrencyPrice(
                5_000f
            ).hashCode()
        )
    }

}
