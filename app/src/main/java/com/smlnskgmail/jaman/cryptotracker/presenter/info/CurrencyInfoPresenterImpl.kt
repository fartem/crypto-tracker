package com.smlnskgmail.jaman.cryptotracker.presenter.info

import android.content.Intent
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

    override fun openCurrencySite() {
        val browserIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(
                currency.currencyType().site
            )
        )
        currencyInfoView.context().startActivity(
            browserIntent
        )
    }

}
