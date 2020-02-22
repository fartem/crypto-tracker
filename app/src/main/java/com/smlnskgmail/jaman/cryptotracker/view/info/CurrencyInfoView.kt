package com.smlnskgmail.jaman.cryptotracker.view.info

import android.content.Context
import android.net.Uri
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.Currency

interface CurrencyInfoView {

    fun context(): Context

    fun showCurrencyInfo(
        currency: Currency
    )
    fun openCurrencySite(
        site: Uri
    )

}
