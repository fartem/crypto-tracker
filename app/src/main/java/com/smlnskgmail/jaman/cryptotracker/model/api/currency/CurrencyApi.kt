package com.smlnskgmail.jaman.cryptotracker.model.api.currency

interface CurrencyApi {

    fun currencies(
        currenciesLoadTarget: CurrenciesLoadTarget
    )

    fun currencyListing(
        currency: Currency,
        currencyListingLoadTarget: CurrencyListingLoadTarget
    )

    interface CurrenciesLoadTarget {

        fun loaded(
            currencies: List<Currency>
        )

    }

    interface CurrencyListingLoadTarget {

        fun loaded(
            currencyListing: CurrencyListing?
        )

    }

}
