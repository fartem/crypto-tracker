package com.smlnskgmail.jaman.cryptotracker.model.api.currency

import java.text.DecimalFormat

class CurrencyPricePercentChangeDay(
    private val value: Float
) {

    fun value(): Float {
        return value
    }

    override fun toString(): String {
        return "${DecimalFormat("0.##").format(value)}%"
    }

}
