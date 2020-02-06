package com.smlnskgmail.jaman.cryptotracker.logic.currencies.impl.debug

import android.annotation.SuppressLint
import android.os.AsyncTask
import com.smlnskgmail.jaman.cryptotracker.logic.currencies.api.Currency
import com.smlnskgmail.jaman.cryptotracker.logic.currencies.api.CurrencyApi
import com.smlnskgmail.jaman.cryptotracker.logic.currencies.api.CurrencyListing
import com.smlnskgmail.jaman.cryptotracker.logic.currencies.api.CurrencyType

@Suppress("MagicNumber", "unused")
class DebugCurrencyApi : CurrencyApi {

    private val date = "2019-03-05T18:05:05.000Z"

    private val currencies = hashMapOf(
        1 to currencyFor(
            1,
            "Bitcoin",
            CurrencyType.BTC
        ),
        2 to currencyFor(
            2,
            "Ethereum",
            CurrencyType.ETH
        ),
        3 to currencyFor(
            3,
            "Litecoin",
            CurrencyType.LTC
        ),
        4 to currencyFor(
            4,
            "Binance",
            CurrencyType.BNB
        ),
        5 to currencyFor(
            5,
            "Dash",
            CurrencyType.DASH
        ),
        6 to currencyFor(
            6,
            "Makerdao",
            CurrencyType.MKR
        ),
        7 to currencyFor(
            7,
            "Bytom",
            CurrencyType.BTM
        ),
        8 to currencyFor(
            8,
            "Aeternity",
            CurrencyType.AE
        ),
        9 to currencyFor(
            9,
            "Monolith",
            CurrencyType.TKN
        ),
        10 to currencyFor(
            10,
            "Medibloc",
            CurrencyType.MED
        )
    )

    private fun currencyFor(
        id: Int,
        name: String,
        type: CurrencyType
    ): Currency {
        return Currency(
            id,
            name,
            type.currencySymbol,
            type.currencySymbol,
            date,
            date,
            CurrencyListing(
                1000f,
                0f
            ),
            type
        )
    }

    override fun currencies(
        currenciesLoadResult: CurrencyApi.CurrenciesLoadResult
    ) {
        CurrenciesLoader(currenciesLoadResult).execute()
    }

    override fun currencyListing(
        currencyId: Int,
        currencyListingLoadResult: CurrencyApi.CurrencyListingLoadResult
    ) {
        val currency = currencies[currencyId]
        currencyListingLoadResult.loaded(
            currency!!.listing
        )
    }

    @SuppressLint("StaticFieldLeak")
    private inner class CurrenciesLoader(
        private val currenciesLoadResult: CurrencyApi.CurrenciesLoadResult
    ) : AsyncTask<Void, List<Currency>, List<Currency>>() {

        override fun doInBackground(vararg params: Void?): List<Currency> {
            Thread.sleep(1_000)
            return currencies.values.toList()
        }

        override fun onPostExecute(result: List<Currency>?) {
            currenciesLoadResult.loaded(result!!)
        }

    }

}
