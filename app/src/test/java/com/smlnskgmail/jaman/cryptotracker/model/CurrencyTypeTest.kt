package com.smlnskgmail.jaman.cryptotracker.model

import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyPrice
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyPricePercentChangeDay
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyType
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcup.CmcCurrency
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcup.CmcCurrencyListing
import org.junit.Assert.assertEquals
import org.junit.Test

class CurrencyTypeTest {

    @Test
    fun validateSupportedSymbols() {
        assertEquals(
            CurrencyType.values().size,
            CurrencyType.supportedSymbols().size
        )
    }

    @Test
    fun validateTypeForCurrency() {
        val currencyType = CurrencyType.BTC
        val currency = CmcCurrency(
            1,
            "BTC",
            "BTC",
            "BTC",
            "-",
            "-",
            CmcCurrencyListing(
                CurrencyPrice(
                    1_000f
                ),
                CurrencyPricePercentChangeDay(
                    1f
                )
            ),
            currencyType
        )

        assertEquals(
            currencyType,
            CurrencyType.typeForCurrency(
                currency
            )
        )
    }

}
