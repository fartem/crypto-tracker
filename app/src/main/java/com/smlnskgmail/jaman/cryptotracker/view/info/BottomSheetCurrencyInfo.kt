package com.smlnskgmail.jaman.cryptotracker.view.info

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.smlnskgmail.jaman.cryptotracker.R
import com.smlnskgmail.jaman.cryptotracker.components.bottomsheets.BaseBottomSheet
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.Currency
import com.smlnskgmail.jaman.cryptotracker.presenter.info.CurrencyInfoPresenter
import com.smlnskgmail.jaman.cryptotracker.presenter.info.CurrencyInfoPresenterImpl
import kotlinx.android.synthetic.main.bottom_sheet_currency.*
import java.text.SimpleDateFormat

class BottomSheetCurrencyInfo : BaseBottomSheet(), CurrencyInfoView {

    private lateinit var currencyInfoPresenter: CurrencyInfoPresenter

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(
            view,
            savedInstanceState
        )
        currencyInfoPresenter = CurrencyInfoPresenterImpl()
        currencyInfoPresenter.init(
            this,
            arguments!!.getSerializable("currency") as Currency
        )
    }

    override fun layoutResId(): Int {
        return R.layout.bottom_sheet_currency
    }

    override fun context(): Context {
        return context!!
    }

    override fun showCurrencyInfo(currency: Currency) {
        currency_image.setImageDrawable(
            ContextCompat.getDrawable(
                context!!,
                currency.currencyType().iconResId
            )
        )
        currency_name.text = currency.name()

        currency_symbol.text = currency.symbol()
        currency_symbol.setTextColor(
            Color.parseColor(currency.currencyType().accentColor)
        )

        currency_slug.text = currency.slug()

        currency_first_historical_data.text = dateFromTimestamp(
            currency.firstHistoricalData()
        )
        currency_last_historical_data.text = dateFromTimestamp(
            currency.lastHistoricalData()
        )

        currency_price.text = currency.currencyListing().currentPrice().toString()

        currency_site.background.setTint(
            Color.parseColor(
                currency.currencyType().accentColor
            )
        )

        currency_site.setOnClickListener {
            currencyInfoPresenter.openCurrencySite(
                currency
            )
        }
    }

    override fun openCurrencySite(site: Uri) {
        val browserIntent = Intent(
            Intent.ACTION_VIEW,
            site
        )
        startActivity(browserIntent)
    }

    @SuppressLint("SimpleDateFormat")
    private fun dateFromTimestamp(
        timestamp: String
    ): String {
        val simpleDateFormat = SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        )
        val formattedDate = simpleDateFormat.parse(
            timestamp
        )!!

        val outputFormat = SimpleDateFormat(
            "yyyy-MM-dd"
        )
        return outputFormat.format(
            formattedDate
        ).toString()
    }

}
