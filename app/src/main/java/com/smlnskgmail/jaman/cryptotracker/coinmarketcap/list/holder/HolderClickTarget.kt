package com.smlnskgmail.jaman.cryptotracker.coinmarketcap.list.holder

import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.model.Currency

interface HolderClickTarget {

    fun holderItemClick(
        currency: Currency
    )

}