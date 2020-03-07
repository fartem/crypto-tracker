package com.smlnskgmail.jaman.cryptotracker.model.cache.mapdb

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyType
import com.smlnskgmail.jaman.cryptotracker.model.impl.cache.mapdb.MapDbCurrencyCache
import com.smlnskgmail.jaman.cryptotracker.model.impl.cache.mapdb.MapDbCurrencySerializer
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.debug.DebugCurrency
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.debug.DebugCurrencyMapDbInstanceProvider
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class MapDbCacheTest {

    @Test
    fun debugCacheValidation() {
        val cache = MapDbCurrencyCache(
            InstrumentationRegistry.getInstrumentation().targetContext,
            MapDbCurrencySerializer(
                DebugCurrencyMapDbInstanceProvider()
            )
        )

        cache.clear()
        assertEquals(
            0,
            cache.getCurrencies().size
        )

        val currency = DebugCurrency(
            "Bitcoin",
            CurrencyType.BTC.currencySymbol,
            CurrencyType.BTC.currencySymbol,
            Date().toString(),
            Date().toString(),
            CurrencyType.BTC
        )

        cache.putCurrencies(
            listOf(
                currency
            )
        )
        assertEquals(
            1,
            cache.getCurrencies().size
        )

        cache.updateCurrency(
            currency
        )
        assertEquals(
            1,
            cache.getCurrencies().size
        )

        val currencyFromCache = cache.getCurrencies()[0]
        assertEquals(
            currency,
            currencyFromCache
        )
    }

}
