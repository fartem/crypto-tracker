package com.smlnskgmail.jaman.cryptotracker.presenter.info

import android.os.Bundle
import com.smlnskgmail.jaman.cryptotracker.view.info.CurrencyInfoView

interface CurrencyInfoPresenter {

    fun init(
        currencyInfoView: CurrencyInfoView,
        arguments: Bundle
    )

    fun openCurrencySite()

}
