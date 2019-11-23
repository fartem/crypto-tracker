package com.smlnskgmail.jaman.cryptotracker.coinmarketcap.loaders.currencies

import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.api.CurrencyApi
import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.model.Currency

interface CurrencyListLoaderTarget {

    fun currencyListLoaderResult(
        currencies: List<Currency>,
        throwable: Throwable?,
        api: CurrencyApi
    )

}