package com.smlnskgmail.jaman.cryptotracker.logic.api

import com.smlnskgmail.jaman.cryptotracker.logic.api.entities.Currency
import com.smlnskgmail.jaman.cryptotracker.logic.api.entities.CurrencyListing

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
