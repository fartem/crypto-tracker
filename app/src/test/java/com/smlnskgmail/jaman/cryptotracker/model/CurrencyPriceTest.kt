package com.smlnskgmail.jaman.cryptotracker.model

import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyPrice
import org.junit.Assert.assertEquals
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

}
