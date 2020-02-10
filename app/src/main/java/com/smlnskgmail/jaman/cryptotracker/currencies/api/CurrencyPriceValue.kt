package com.smlnskgmail.jaman.cryptotracker.currencies.api

import java.text.DecimalFormat

class CurrencyPriceValue(
    private val price: Float
) {

    fun value(): Float {
        return price
    }

    override fun toString(): String {
        return DecimalFormat("0.###").format(price)
    }

}
