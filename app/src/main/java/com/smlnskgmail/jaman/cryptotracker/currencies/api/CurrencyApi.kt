package com.smlnskgmail.jaman.cryptotracker.currencies.api

interface CurrencyApi {

    fun currencies(
        currenciesLoadResult: CurrenciesLoadResult
    )

    fun currencyListing(
        currency: Currency,
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
