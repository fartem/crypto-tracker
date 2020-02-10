package com.smlnskgmail.jaman.cryptotracker.currencies.impl.debug

import android.annotation.SuppressLint
import android.os.AsyncTask
import com.smlnskgmail.jaman.cryptotracker.currencies.api.*

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
        return object : Currency {

            private var currencyListing: CurrencyListing? = null

            override fun id(): Int {
                return id
            }

            override fun name(): String {
                return name
            }

            override fun symbol(): String {
                return type.currencySymbol
            }

            override fun slug(): String {
                return type.currencySymbol
            }

            override fun firstHistoricalData(): String {
                return date
            }

            override fun lastHistoricalData(): String {
                return date
            }

            override fun updateCurrencyListing(
                currencyListing: CurrencyListing
            ) {
                this.currencyListing = currencyListing
            }

            override fun currencyListing(): CurrencyListing {
                if (currencyListing == null) {
                    currencyListing = object : CurrencyListing {
                        override fun currentPrice(): CurrencyPriceValue {
                            return CurrencyPriceValue(
                                1000.751f
                            )
                        }

                        override fun changeHour(): CurrencyPriceValue {
                            return CurrencyPriceValue(
                                0.19f
                            )
                        }
                    }
                }
                return currencyListing!!
            }

            override fun currencyType(): CurrencyType {
                return type
            }
        }
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
            currency!!.currencyListing()
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
