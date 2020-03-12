package com.smlnskgmail.jaman.cryptotracker

import android.app.Application
import android.content.Context
import com.smlnskgmail.jaman.cryptotracker.di.components.ApplicationComponent
import com.smlnskgmail.jaman.cryptotracker.di.components.DaggerApplicationComponent
import com.smlnskgmail.jaman.cryptotracker.di.modules.CurrencyApiModule
import com.smlnskgmail.jaman.cryptotracker.di.modules.CurrencyCacheModule
import com.smlnskgmail.jaman.cryptotracker.model.api.cache.CurrencyCache
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyApi
import com.smlnskgmail.jaman.cryptotracker.model.impl.cache.mapdb.MapDbCurrencyCache
import com.smlnskgmail.jaman.cryptotracker.model.impl.cache.mapdb.MapDbCurrencySerializer
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcup.CmcCurrencyApi
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.coinmarketcup.cache.CmcCurrencyMapDbInstanceProvider
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.debug.DebugCurrencyApi
import com.smlnskgmail.jaman.cryptotracker.model.impl.currency.debug.DebugCurrencyMapDbInstanceProvider

class App : Application() {

    companion object {

        lateinit var applicationComponent: ApplicationComponent

    }

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder()
            .currencyApiModule(
                CurrencyApiModule(
                    currencyApi()
                )
            )
            .currencyCacheModule(
                CurrencyCacheModule(
                    currencyCache(
                        this
                    )
                )
            )
            .build()
    }

    @Suppress("ConstantConditionIf")
    fun currencyApi(): CurrencyApi {
        return if (BuildConfig.API == "DEBUG") {
            DebugCurrencyApi()
        } else {
            CmcCurrencyApi()
        }
    }

    @Suppress("ConstantConditionIf")
    fun currencyCache(context: Context): CurrencyCache {
        val instanceProvider = if (BuildConfig.API == "DEBUG") {
            DebugCurrencyMapDbInstanceProvider()
        } else {
            CmcCurrencyMapDbInstanceProvider()
        }
        return MapDbCurrencyCache(
            context,
            MapDbCurrencySerializer(
                instanceProvider
            )
        )
    }

}
