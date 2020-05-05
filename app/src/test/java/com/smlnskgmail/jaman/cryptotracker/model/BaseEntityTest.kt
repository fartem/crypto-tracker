package com.smlnskgmail.jaman.cryptotracker.model

import org.junit.Test

abstract class BaseEntityTest {

    @Test
    abstract fun `Validate fields`()

    @Test
    abstract fun `Validate equals()`()

    @Test
    abstract fun `Validate hashCode()`()

}
