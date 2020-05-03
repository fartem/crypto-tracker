package com.smlnskgmail.jaman.cryptotracker

import com.smlnskgmail.jaman.cryptotracker.model.api.CurrencyPricePercentChangeDayTest
import com.smlnskgmail.jaman.cryptotracker.model.api.CurrencyPriceTest
import com.smlnskgmail.jaman.cryptotracker.model.api.CurrencyTypeTest
import com.smlnskgmail.jaman.cryptotracker.model.impl.coinmarketcap.CmcCurrencyListingTest
import com.smlnskgmail.jaman.cryptotracker.model.impl.coinmarketcap.CmcCurrencyTest
import com.smlnskgmail.jaman.cryptotracker.model.impl.debug.DebugCurrencyTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@Suite.SuiteClasses(
    CurrencyPriceTest::class,
    CurrencyPricePercentChangeDayTest::class,
    CurrencyTypeTest::class,
    CmcCurrencyTest::class,
    CmcCurrencyListingTest::class,
    DebugCurrencyTest::class
)
@RunWith(Suite::class)
class TestSuite
