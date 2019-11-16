package com.smlnskgmail.jaman.cryptotracker.list.holder

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.smlnskgmail.jaman.cryptotracker.R
import com.smlnskgmail.jaman.cryptotracker.api.CurrencyApi
import com.smlnskgmail.jaman.cryptotracker.api.cache.CurrencyListingCache
import com.smlnskgmail.jaman.cryptotracker.api.responses.CurrencyListingResponse
import com.smlnskgmail.jaman.cryptotracker.model.Currency
import com.smlnskgmail.jaman.cryptotracker.model.CurrencyListing
import com.smlnskgmail.jaman.cryptotracker.model.CurrencyMedia
import kotlinx.android.synthetic.main.item_currency.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrencyHolder(
    itemView: View,
    private val holderClickTarget: HolderClickTarget
) : RecyclerView.ViewHolder(
    itemView
) {

    fun bind(
        currency: Currency,
        currencyMedia: CurrencyMedia
    ) {
        resetData()
        itemView.currency_image.setImageDrawable(
            ContextCompat.getDrawable(
                itemView.context,
                currencyMedia.iconResId
            )
        )
        itemView.currency_symbol.text = currency.symbol
        itemView.currency_symbol.setTextColor(
            Color.parseColor(
                currencyMedia.accentColor
            )
        )

        itemView.currency_name.text = currency.name

        itemView.currency_refresh_listing.setOnClickListener {
            loadCurrencyListing(
                currency
            )
        }

        itemView.setOnClickListener {
            holderClickTarget.holderItemClick(currency)
        }

        if (CurrencyListingCache.loadFor(
                currency
            ) == null) {
            loadCurrencyListing(
                currency
            )
        } else {
            showCurrencyListing(
                CurrencyListingCache.loadFor(
                    currency
                )!!
            )
        }
    }

    private fun loadCurrencyListing(
        currency: Currency
    ) {
        CurrencyApi.currencyService().listing(
            currency.id
        ).enqueue(
            object : Callback<CurrencyListingResponse> {
                override fun onFailure(
                    call: Call<CurrencyListingResponse>,
                    t: Throwable
                ) {
                    val currencyListing = CurrencyListing(
                        0.0f,
                        0.0f,
                        "-"
                    )
                    CurrencyListingCache.putFor(
                        currency,
                        currencyListing
                    )
                    showErrorInfo()
                }

                @SuppressLint("SetTextI18n")
                override fun onResponse(
                    call: Call<CurrencyListingResponse>,
                    response: Response<CurrencyListingResponse>
                ) {
                    val currencyListing = response.body()!!.currencyListing
                    CurrencyListingCache.putFor(
                        currency,
                        currencyListing
                    )
                    showCurrencyListing(
                        currencyListing
                    )
                }
            }
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

    private fun formattedCurrencyPrice(
        price: Float
    ): String {
        return "%.3f $".format(
            price
        )
    }

    private fun resetData() {
        itemView.currency_symbol.text = ""
        itemView.currency_name.text = ""
        itemView.currency_price.text = ""
    }

    private fun showErrorInfo() {
        itemView.currency_symbol.text = "-"
        itemView.currency_name.text = "-"
        itemView.currency_price.text = "-"
    }

}