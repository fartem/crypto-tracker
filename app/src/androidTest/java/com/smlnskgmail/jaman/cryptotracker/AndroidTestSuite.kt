package com.smlnskgmail.jaman.cryptotracker

import com.smlnskgmail.jaman.cryptotracker.api.parsing.CurrencyDeserializerTest
import com.smlnskgmail.jaman.cryptotracker.api.parsing.CurrencyListingDeserializerTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@Suite.SuiteClasses(
    CurrencyDeserializerTest::class,
    CurrencyListingDeserializerTest::class
)
@RunWith(Suite::class)
class AndroidTestSuite
