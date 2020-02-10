package com.smlnskgmail.jaman.cryptotracker.currencies.api

interface CurrencyApi {

    fun currencies(
        currenciesLoadResult: CurrenciesLoadResult
    )

    fun currencyListing(
        currencyId: Int,
        currencyListingLoadResult: CurrencyListingLoadResult
    )

    interface CurrenciesLoadResult {

        fun loaded(
            currencies: List<Currency>
        )

    }

    interface CurrencyListingLoadResult {

        fun loaded(
            currencyListing: CurrencyListing?
        )

    }

}
