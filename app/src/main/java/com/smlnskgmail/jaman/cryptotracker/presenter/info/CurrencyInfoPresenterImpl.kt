package com.smlnskgmail.jaman.cryptotracker.presenter.info

import android.net.Uri
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.Currency
import com.smlnskgmail.jaman.cryptotracker.view.info.CurrencyInfoView

class CurrencyInfoPresenterImpl : CurrencyInfoPresenter {

    private lateinit var currencyInfoView: CurrencyInfoView
    private lateinit var currency: Currency

    override fun init(
        currencyInfoView: CurrencyInfoView,
        currency: Currency
    ) {
        currencyInfoView.showCurrencyInfo(currency)
    }

    override fun openCurrencySite(
        currency: Currency
    ) {
        currencyInfoView.openCurrencySite(
            Uri.parse(
                currency.currencyType().site
            )
        )
    }

}
