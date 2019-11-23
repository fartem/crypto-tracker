package com.smlnskgmail.jaman.cryptotracker.coinmarketcap.loaders.price

import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.api.CurrencyApi
import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.model.Currency
import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.model.CurrencyListing

interface CurrencyPriceLoaderTarget {

    fun currencyPriceLoaderResult(
        currency: Currency,
        currencyListing: CurrencyListing?,
        throwable: Throwable?,
        api: CurrencyApi
    )

}