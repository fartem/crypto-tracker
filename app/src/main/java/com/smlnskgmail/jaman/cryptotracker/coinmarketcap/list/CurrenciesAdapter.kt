package com.smlnskgmail.jaman.cryptotracker.coinmarketcap.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smlnskgmail.jaman.cryptotracker.R
import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.api.CurrencyApi
import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.list.holder.CurrencyHolder
import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.list.holder.CurrencyHolderClickTarget
import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.loaders.price.CurrencyPriceLoaderTarget
import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.model.Currency
import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.model.CurrencyMedia

class CurrenciesAdapter(
    private val currencies: List<Currency>,
    private val currencyHolderClickTarget: CurrencyHolderClickTarget,
    private val currencyPriceLoaderTarget: CurrencyPriceLoaderTarget,
    private val api: CurrencyApi
) : RecyclerView.Adapter<CurrencyHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CurrencyHolder {
        return CurrencyHolder(
            LayoutInflater.from(
                parent.context
            ).inflate(
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
            CurrencyMedia.mediaForCurrency(
                currency
            )
        )
    }

    fun refreshCurrency(
        currency: Currency
    ) {
        notifyItemChanged(
            currencies.indexOf(
                currency
            )
        )
    }

    override fun getItemCount(): Int {
        return currencies.count()
    }

}