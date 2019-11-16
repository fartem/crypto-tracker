package com.smlnskgmail.jaman.cryptotracker.list.holder

import com.smlnskgmail.jaman.cryptotracker.model.Currency

interface HolderClickTarget {

    fun holderItemClick(
        currency: Currency
    )

}