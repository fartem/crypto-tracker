package com.smlnskgmail.jaman.cryptotracker.di.components

import com.smlnskgmail.jaman.cryptotracker.MainActivity
import com.smlnskgmail.jaman.cryptotracker.di.modules.CurrencyApiModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CurrencyApiModule::class
    ]
)
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder {

        fun withCurrenciesApi(
            currencyApiModule: CurrencyApiModule
        ): Builder

        fun build(): ApplicationComponent

    }

}
