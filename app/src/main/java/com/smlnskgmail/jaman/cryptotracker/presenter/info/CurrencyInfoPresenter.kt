package com.smlnskgmail.jaman.cryptotracker.presenter.info

import android.os.Bundle
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.Currency
import com.smlnskgmail.jaman.cryptotracker.view.info.CurrencyInfoView

interface CurrencyInfoPresenter {

    fun init(
        currencyInfoView: CurrencyInfoView,
        currency: Currency
    )

    fun openCurrencySite(
        currency: Currency
    )

}
