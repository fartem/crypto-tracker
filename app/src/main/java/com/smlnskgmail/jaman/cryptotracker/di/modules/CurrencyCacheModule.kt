package com.smlnskgmail.jaman.cryptotracker.di.modules

import com.smlnskgmail.jaman.cryptotracker.model.api.cache.CurrencyCache
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CurrencyCacheModule(
    private val currencyCache: CurrencyCache
) {

    @Singleton
    @Provides
    fun currencyCache(): CurrencyCache {
        return currencyCache
    }

}
