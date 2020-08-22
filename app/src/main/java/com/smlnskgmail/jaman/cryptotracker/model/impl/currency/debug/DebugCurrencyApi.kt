package com.smlnskgmail.jaman.cryptotracker.model.impl.currency.debug

import com.smlnskgmail.jaman.cryptotracker.model.api.currency.Currency
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyApi
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyType
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

@Suppress(
    "unused",
    "MagicNumber"
)
class DebugCurrencyApi :
    CurrencyApi {

    private val date = "2019-03-05T18:05:05.000Z"

    private val currencies = listOf(
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
        currenciesLoadTarget: CurrencyApi.CurrenciesLoadTarget
    ) {
        Observable
            .timer(5, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { currenciesLoadTarget.loaded(currencies) }
    }

    override fun currencyListing(
        currency: Currency,
        currencyListingLoadTarget: CurrencyApi.CurrencyListingLoadTarget
    ) {
        currencyListingLoadTarget.loaded(
            currency.currencyListing()
        )
    }

}
