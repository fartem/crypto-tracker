package com.smlnskgmail.jaman.cryptotracker

import android.app.Application
import com.smlnskgmail.jaman.cryptotracker.di.components.ApplicationComponent
import com.smlnskgmail.jaman.cryptotracker.di.components.DaggerApplicationComponent
import com.smlnskgmail.jaman.cryptotracker.di.modules.CurrencyApiModule
import com.smlnskgmail.jaman.cryptotracker.logic.currencies.impl.coinmarketcup.CmcCurrencyApi
import com.smlnskgmail.jaman.cryptotracker.logic.currencies.impl.debug.DebugCurrencyApi

class App : Application() {

    companion object {
        lateinit var applicationComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        val currencyApi = if (BuildConfig.API_IMPL == "DEBUG") {
            DebugCurrencyApi()
        } else {
            CmcCurrencyApi()
        }
        applicationComponent = DaggerApplicationComponent
            .builder()
            .withCurrenciesApi(
                CurrencyApiModule(
                    currencyApi
                )
            )
            .build()
    }

}
