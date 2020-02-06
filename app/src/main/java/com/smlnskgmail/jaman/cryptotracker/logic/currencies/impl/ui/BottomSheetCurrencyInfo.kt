package com.smlnskgmail.jaman.cryptotracker.logic.currencies.impl.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smlnskgmail.jaman.cryptotracker.R
import com.smlnskgmail.jaman.cryptotracker.logic.currencies.api.Currency
import kotlinx.android.synthetic.main.bottom_sheet_currency.*
import java.text.SimpleDateFormat

class BottomSheetCurrencyInfo : BottomSheetDialogFragment() {

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(
            view,
            savedInstanceState
        )

        val currency = arguments?.getSerializable(
            "currency"
        ) as Currency


        currency_image.setImageDrawable(
            ContextCompat.getDrawable(
                context!!,
                currency.currencyType().iconResId
            )
        )

        currency_id.text = currency.id().toString()
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

        currency_price.text = "%.2f $".format(currency.currencyListing().price())

        currency_site.background.setTint(
            Color.parseColor(currency.currencyType().accentColor)
        )
        currency_site.setOnClickListener {
            val browserIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(currency.currencyType().site)
            )
            startActivity(browserIntent)
        }
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.bottom_sheet_currency,
            container,
            false
        )
    }

}
