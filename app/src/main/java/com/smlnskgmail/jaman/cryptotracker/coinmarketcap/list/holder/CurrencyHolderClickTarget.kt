package com.smlnskgmail.jaman.cryptotracker.coinmarketcap.list.holder

import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.model.Currency

interface CurrencyHolderClickTarget {

    fun onCurrencyRefreshClick(
        currency: Currency
    )

}