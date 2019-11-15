package com.smlnskgmail.jaman.cryptotracker.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smlnskgmail.jaman.cryptotracker.R
import com.smlnskgmail.jaman.cryptotracker.model.Currency

class CurrenciesAdapter(
    private val currencies: List<Currency>
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
            )
        )
    }

    override fun onBindViewHolder(
        holder: CurrencyHolder,
        position: Int
    ) {
        holder.bind(
            currencies[position]
        )
    }

    override fun getItemCount(): Int {
        return currencies.count()
    }


}