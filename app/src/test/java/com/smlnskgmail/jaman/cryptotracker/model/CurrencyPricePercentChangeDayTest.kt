package com.smlnskgmail.jaman.cryptotracker.model

import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyPricePercentChangeDay
import org.junit.Assert.assertEquals
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

}
