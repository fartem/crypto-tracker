package com.smlnskgmail.jaman.cryptotracker.currencies.impl.debug

import android.annotation.SuppressLint
import android.os.AsyncTask
import com.smlnskgmail.jaman.cryptotracker.currencies.api.Currency
import com.smlnskgmail.jaman.cryptotracker.currencies.api.CurrencyApi
import com.smlnskgmail.jaman.cryptotracker.currencies.api.CurrencyType

@Suppress(
    "unused",
    "MagicNumber"
)
class DebugCurrencyApi : CurrencyApi {

    private val date = "2019-03-05T18:05:05.000Z"

    private val currencies = setOf(
        currencyFor(
            "Bitcoin",
            CurrencyType.BTC
        ),
        currencyFor(
            "Ethereum",
            CurrencyType.ETH
        ),
        currencyFor(
            "Litecoin",
            CurrencyType.LTC
        ),
        currencyFor(
            "Binance",
            CurrencyType.BNB
        ),
        currencyFor(
            "Dash",
            CurrencyType.DASH
        ),
        currencyFor(
            "Makerdao",
            CurrencyType.MKR
        ),
        currencyFor(
            "Bytom",
            CurrencyType.BTM
        ),
        currencyFor(
            "Aeternity",
            CurrencyType.AE
        ),
        currencyFor(
            "Monolith",
            CurrencyType.TKN
        ),
        currencyFor(
            "Medibloc",
            CurrencyType.MED
        )
    )

    private fun currencyFor(
        name: String,
        type: CurrencyType
    ): Currency {
        return DebugCurrency(
            name,
            type.currencySymbol,
            type.currencySymbol,
            date,
            date,
            type
        )
    }

    override fun currencies(
        currenciesLoadResult: CurrencyApi.CurrenciesLoadResult
    ) {
        CurrenciesLoader(currenciesLoadResult).execute()
    }

    override fun currencyListing(
        currency: Currency,
        currencyListingLoadResult: CurrencyApi.CurrencyListingLoadResult
    ) {
        currencyListingLoadResult.loaded(
            currency.currencyListing()
        )
    }

    @SuppressLint("StaticFieldLeak")
    private inner class CurrenciesLoader(
        private val currenciesLoadResult: CurrencyApi.CurrenciesLoadResult
    ) : AsyncTask<Void, List<Currency>, List<Currency>>() {

        override fun doInBackground(vararg params: Void?): List<Currency> {
            Thread.sleep(5_000)
            return currencies.toList()
        }

        override fun onPostExecute(result: List<Currency>?) {
            currenciesLoadResult.loaded(result!!)
        }

    }

}
