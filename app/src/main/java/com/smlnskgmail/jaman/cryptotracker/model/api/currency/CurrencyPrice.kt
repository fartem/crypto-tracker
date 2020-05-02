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

}
