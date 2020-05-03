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

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (javaClass != other?.javaClass) {
            return false
        }

        other as CurrencyPricePercentChangeDay

        if (value != other.value) {
            return false
        }
        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }


}
