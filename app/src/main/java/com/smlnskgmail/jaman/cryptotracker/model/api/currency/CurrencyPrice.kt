package com.smlnskgmail.jaman.cryptotracker.model.api.currency

import java.text.NumberFormat
import java.util.*

data class CurrencyPrice(
    val price: Float
) {

    override fun toString(): String {
        return NumberFormat.getCurrencyInstance(
            Locale.US
        ).format(price)
    }

}
