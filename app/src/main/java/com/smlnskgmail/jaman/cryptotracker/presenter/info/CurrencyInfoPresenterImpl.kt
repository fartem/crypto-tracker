package com.smlnskgmail.jaman.cryptotracker.presenter.info

import android.net.Uri
import android.os.Bundle
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.Currency
import com.smlnskgmail.jaman.cryptotracker.view.info.CurrencyInfoView

class CurrencyInfoPresenterImpl : CurrencyInfoPresenter {

    private lateinit var currencyInfoView: CurrencyInfoView
    private lateinit var currency: Currency

    override fun init(
        currencyInfoView: CurrencyInfoView,
        arguments: Bundle
    ) {
        this.currencyInfoView = currencyInfoView
        currency = arguments.getSerializable(
            "currency"
        ) as Currency
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
