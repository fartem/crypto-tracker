package com.smlnskgmail.jaman.cryptotracker.model.api.currency

import java.text.DecimalFormat

data class CurrencyPricePercentChangeDay(
    val value: Float
) {

    override fun toString(): String {
        return "${DecimalFormat("0.##").format(value)}%"
    }

}
