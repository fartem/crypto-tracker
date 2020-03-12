package com.smlnskgmail.jaman.cryptotracker.di.modules

import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyApi
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
