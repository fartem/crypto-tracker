package com.smlnskgmail.jaman.cryptotracker.di.modules

import com.smlnskgmail.jaman.cryptotracker.logic.currencies.api.CurrencyApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CurrencyApiModule(
    private val currencyApi: CurrencyApi
) {

    @Singleton
    @Provides
    fun currencyApi(): CurrencyApi {
        return currencyApi
    }

}
