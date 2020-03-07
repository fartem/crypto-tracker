package com.smlnskgmail.jaman.cryptotracker

import com.smlnskgmail.jaman.cryptotracker.debug.DebugCurrenciesListTest
import com.smlnskgmail.jaman.cryptotracker.model.cache.mapdb.MapDbCacheTest
import com.smlnskgmail.jaman.cryptotracker.model.currency.cmc.CurrencyDeserializerTest
import com.smlnskgmail.jaman.cryptotracker.model.currency.cmc.CurrencyListingDeserializerTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@Suite.SuiteClasses(
    CurrencyDeserializerTest::class,
    CurrencyListingDeserializerTest::class,
    DebugCurrenciesListTest::class,
    MapDbCacheTest::class
)
@RunWith(Suite::class)
class AndroidTestSuite
