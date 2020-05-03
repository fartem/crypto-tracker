package com.smlnskgmail.jaman.cryptotracker.model.api.currency

import java.text.NumberFormat
import java.util.*

class CurrencyPrice(
    private val price: Float
) {

    fun value(): Float {
        return price
    }

    override fun toString(): String {
        val numberFormat = NumberFormat.getCurrencyInstance(
            Locale.US
        )
        return numberFormat.format(
            price
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (javaClass != other?.javaClass) {
            return false
        }

        other as CurrencyPrice

        if (price != other.price) {
            return false
        }
        return true
    }

    override fun hashCode(): Int {
        return price.hashCode()
    }

}
