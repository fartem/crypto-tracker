package com.smlnskgmail.jaman.cryptotracker.components.currencieslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smlnskgmail.jaman.cryptotracker.R
import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.api.CurrencyApi
import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.loaders.CurrencyPriceLoader
import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.model.Currency
import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.model.CurrencyEntitiy

class CurrenciesAdapter(
    private val currencies: List<Currency>,
    private val currencyHolderClickTarget: CurrencyHolder.CurrencyHolderClickTarget,
    private val currencyPriceLoaderTarget: CurrencyPriceLoader.CurrencyPriceLoaderTarget,
    private val api: CurrencyApi
) : RecyclerView.Adapter<CurrencyHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CurrencyHolder {
        return CurrencyHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_currency,
                parent,
                false
            ),
            currencyHolderClickTarget,
            currencyPriceLoaderTarget,
            api
        )
    }

    override fun onBindViewHolder(
        holder: CurrencyHolder,
        position: Int
    ) {
        val currency = currencies[position]
        holder.bind(
            currency,
            CurrencyEntitiy.mediaForCurrency(currency)
        )
    }

    fun refreshCurrency(
        currency: Currency
    ) {
        notifyItemChanged(
            currencies.indexOf(currency)
        )
    }

    override fun getItemCount(): Int {
        return currencies.count()
    }

}