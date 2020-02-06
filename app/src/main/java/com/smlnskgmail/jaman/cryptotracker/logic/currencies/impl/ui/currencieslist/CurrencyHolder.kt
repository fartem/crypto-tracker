package com.smlnskgmail.jaman.cryptotracker.logic.currencies.impl.ui.currencieslist

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.smlnskgmail.jaman.cryptotracker.R
import com.smlnskgmail.jaman.cryptotracker.logic.currencies.api.Currency
import com.smlnskgmail.jaman.cryptotracker.logic.currencies.api.CurrencyListing
import kotlinx.android.synthetic.main.item_currency.view.*

class CurrencyHolder(
    itemView: View,
    private val currencyClickTarget: CurrencyClickTarget,
    private val currencyRefreshClickTarget: CurrencyRefreshClickTarget
) : RecyclerView.ViewHolder(
    itemView
) {

    fun bind(currency: Currency) {
        resetData()
        itemView.currency_image.setImageDrawable(
            ContextCompat.getDrawable(
                itemView.context,
                currency.type.iconResId
            )
        )

        val accentColor = Color.parseColor(
            currency.type.accentColor
        )

        itemView.currency_symbol.text = currency.symbol
        itemView.currency_symbol.setTextColor(accentColor)

        itemView.currency_name.text = currency.name

        itemView.currency_refresh_listing.drawable.setTint(
            accentColor
        )
        itemView.currency_refresh_listing.setOnClickListener {
            currencyRefreshClickTarget.onCurrencyRefreshClick(currency)
        }

        itemView.setOnClickListener {
            currencyClickTarget.onCurrencyClick(currency)
        }

        showCurrencyListing(
            currency.listing
        )
    }

    @SuppressLint("SetTextI18n")
    private fun showCurrencyListing(
        currencyListing: CurrencyListing
    ) {
        val price = currencyListing.price
        val changeHour = currencyListing.chengeHour

        itemView.currency_price.text = formattedCurrencyPrice(
            price
        )
        itemView.currency_price_at_last_hour.text = formattedCurrencyPrice(
            currencyListing.chengeHour
        )

        val changeHourColorResId = when {
            changeHour > 0 -> R.color.colorCurrencyPriceUp
            changeHour < 0 -> R.color.colorCurrencyPriceDown
            else -> R.color.colorCurrencyPriceStable
        }

        itemView.currency_price_at_last_hour.setTextColor(
            ContextCompat.getColor(
                itemView.context,
                changeHourColorResId
            )
        )
    }

    private fun formattedCurrencyPrice(price: Float): String {
        return "%.3f $".format(price)
    }

    private fun resetData() {
        itemView.currency_symbol.text = ""
        itemView.currency_name.text = ""
        itemView.currency_price.text = ""
        itemView.currency_price_at_last_hour.text = ""
    }

    interface CurrencyClickTarget {

        fun onCurrencyClick(
            currency: Currency
        )

    }

    interface CurrencyRefreshClickTarget {

        fun onCurrencyRefreshClick(
            currency: Currency
        )

    }

}
