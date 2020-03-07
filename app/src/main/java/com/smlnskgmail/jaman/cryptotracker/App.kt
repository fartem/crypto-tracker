package com.smlnskgmail.jaman.cryptotracker

import android.app.Application
import android.content.Context
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

        private var currencyApi: CurrencyApi? = null
        private var currencyCache: CurrencyCache? = null

        @Suppress("ConstantConditionIf")
        fun currencyApi(): CurrencyApi {
            if (currencyApi == null) {
                currencyApi = if (BuildConfig.API_IMPL == "DEBUG") {
                    DebugCurrencyApi()
                } else {
                    CmcCurrencyApi()
                }
            }
            return currencyApi!!
        }

        @Suppress("ConstantConditionIf")
        fun currencyCache(context: Context): CurrencyCache {
            if (currencyCache == null) {
                val instanceProvider = if (BuildConfig.API_IMPL == "DEBUG") {
                    DebugCurrencyMapDbInstanceProvider()
                } else {
                    CmcCurrencyMapDbInstanceProvider()
                }
                currencyCache = MapDbCurrencyCache(
                    context,
                    MapDbCurrencySerializer(
                        instanceProvider
                    )
                )
            }
            return currencyCache!!
        }

    }

}
