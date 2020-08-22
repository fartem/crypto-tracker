package com.smlnskgmail.jaman.cryptotracker.model.api

import com.smlnskgmail.jaman.cryptotracker.model.BaseEntityTest
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyPrice
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals

class CurrencyPriceTest : BaseEntityTest() {

    private val value = 8_000.5f
    private val currencyPrice = CurrencyPrice(
        value
    )

    override fun `Validate fields`() {
        assertEquals(
            value,
            currencyPrice.price
        )
        assertEquals(
            "$8,000.50",
            currencyPrice.toString()
        )
    }

    override fun `Validate equals()`() {
        assertEquals(
            currencyPrice,
            currencyPrice
        )
        assertEquals(
            CurrencyPrice(
                value
            ),
            currencyPrice
        )

        assertNotEquals(
            CurrencyPrice(
                3_000f
            ),
            currencyPrice
        )
        assertNotEquals(
            currencyPrice,
            null
        )
        assertNotEquals(
            currencyPrice,
            "String"
        )
    }

    override fun `Validate hashCode()`() {
        assertEquals(
            currencyPrice.hashCode(),
            currencyPrice.hashCode()
        )
        assertEquals(
            CurrencyPrice(
                value
            ).hashCode(),
            currencyPrice.hashCode()
        )

        assertNotEquals(
            CurrencyPrice(
                3_000f
            ).hashCode(),
            currencyPrice.hashCode()
        )
    }

}
