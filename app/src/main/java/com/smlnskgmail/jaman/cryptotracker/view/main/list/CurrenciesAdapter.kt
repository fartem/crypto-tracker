package com.smlnskgmail.jaman.cryptotracker.view.main.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smlnskgmail.jaman.cryptotracker.R
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.Currency

class CurrenciesAdapter(
    private val currencies: List<Currency>,
    private val currencyClickTarget: CurrencyHolder.CurrencyClickTarget,
    private val currencyRefreshClickTarget: CurrencyHolder.CurrencyRefreshClickTarget
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
            currencyClickTarget,
            currencyRefreshClickTarget
        )
    }

    override fun onBindViewHolder(
        holder: CurrencyHolder,
        position: Int
    ) {
        val currency = currencies[position]
        holder.bind(currency)
    }

    fun refreshCurrency(currency: Currency) {
        notifyItemChanged(
            currencies.indexOf(currency)
        )
    }

    override fun getItemCount(): Int {
        return currencies.count()
    }

}
