package com.smlnskgmail.jaman.cryptotracker

import android.app.Application
import com.smlnskgmail.jaman.cryptotracker.currencies.impl.cache.mapdb.MapDbCurrencyCache
import com.smlnskgmail.jaman.cryptotracker.currencies.impl.cache.mapdb.MapDbCurrencySerializer
import com.smlnskgmail.jaman.cryptotracker.currencies.impl.coinmarketcup.CmcCurrencyApi
import com.smlnskgmail.jaman.cryptotracker.currencies.impl.coinmarketcup.cache.CmcCurrencyMapDbInstanceProvider
import com.smlnskgmail.jaman.cryptotracker.currencies.impl.debug.DebugCurrencyApi
import com.smlnskgmail.jaman.cryptotracker.currencies.impl.debug.DebugCurrencyMapDbInstanceProvider
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

        @Suppress("ConstantConditionIf")
        val currencyApi = if (BuildConfig.API_IMPL == "DEBUG") {
            DebugCurrencyApi()
        } else {
            CmcCurrencyApi()
        }

        @Suppress("ConstantConditionIf")
        val currencyInstanceCacheProvider = if (BuildConfig.API_IMPL == "DEBUG") {
            DebugCurrencyMapDbInstanceProvider()
        } else {
            CmcCurrencyMapDbInstanceProvider()
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
                        MapDbCurrencySerializer(
                            currencyInstanceCacheProvider
                        )
                    )
                )
            )
            .build()
    }

}
