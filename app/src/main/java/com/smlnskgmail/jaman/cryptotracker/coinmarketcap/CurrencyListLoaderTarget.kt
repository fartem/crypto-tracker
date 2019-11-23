package com.smlnskgmail.jaman.cryptotracker.coinmarketcap

import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.model.Currency

interface CurrencyListLoaderTarget {

    fun loaderResult(currencies: List<Currency>, throwable: Throwable?)

}