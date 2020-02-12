package com.smlnskgmail.jaman.cryptotracker.di.components

import com.smlnskgmail.jaman.cryptotracker.MainActivity
import com.smlnskgmail.jaman.cryptotracker.di.modules.CurrencyApiModule
import com.smlnskgmail.jaman.cryptotracker.di.modules.CurrencyCacheModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CurrencyApiModule::class,
        CurrencyCacheModule::class
    ]
)
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder {

        fun withCurrenciesApi(
            currencyApiModule: CurrencyApiModule
        ): Builder

        fun withCurrenciesCache(
            currencyCacheModule: CurrencyCacheModule
        ): Builder

        fun build(): ApplicationComponent

    }

}
