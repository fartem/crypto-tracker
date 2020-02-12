package com.smlnskgmail.jaman.cryptotracker

import android.app.Application
import com.smlnskgmail.jaman.cryptotracker.currencies.impl.cache.MapDbCurrencyCache
import com.smlnskgmail.jaman.cryptotracker.currencies.impl.coinmarketcup.CmcCurrencyApi
import com.smlnskgmail.jaman.cryptotracker.currencies.impl.coinmarketcup.cache.CmcCurrencySerializer
import com.smlnskgmail.jaman.cryptotracker.currencies.impl.debug.DebugCurrencyApi
import com.smlnskgmail.jaman.cryptotracker.di.components.ApplicationComponent
import com.smlnskgmail.jaman.cryptotracker.di.components.DaggerApplicationComponent
import com.smlnskgmail.jaman.cryptotracker.di.modules.CurrencyApiModule
import com.smlnskgmail.jaman.cryptotracker.di.modules.CurrencyCacheModule

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
            .withCurrenciesCache(
                CurrencyCacheModule(
                    MapDbCurrencyCache(
                        this,
                        CmcCurrencySerializer()
                    )
                )
            )
            .build()
    }

}
