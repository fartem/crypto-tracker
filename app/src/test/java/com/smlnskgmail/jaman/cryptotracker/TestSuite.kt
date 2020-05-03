package com.smlnskgmail.jaman.cryptotracker

import com.smlnskgmail.jaman.cryptotracker.model.CurrencyPricePercentChangeDayTest
import com.smlnskgmail.jaman.cryptotracker.model.CurrencyPriceTest
import com.smlnskgmail.jaman.cryptotracker.model.CurrencyTypeTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@Suite.SuiteClasses(
    CurrencyPriceTest::class,
    CurrencyPricePercentChangeDayTest::class,
    CurrencyTypeTest::class
)
@RunWith(Suite::class)
class TestSuite
