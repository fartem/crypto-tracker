package com.smlnskgmail.jaman.cryptotracker.di.components

import com.smlnskgmail.jaman.cryptotracker.di.modules.CurrencyApiModule
import com.smlnskgmail.jaman.cryptotracker.di.modules.CurrencyCacheModule
import com.smlnskgmail.jaman.cryptotracker.view.list.CurrenciesListFragment
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

    fun inject(currenciesListFragment: CurrenciesListFragment)

}
