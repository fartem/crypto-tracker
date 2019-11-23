package com.smlnskgmail.jaman.cryptotracker.coinmarketcap.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smlnskgmail.jaman.cryptotracker.R
import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.list.holder.CurrencyHolder
import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.list.holder.HolderClickTarget
import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.model.Currency
import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.model.CurrencyMedia

class CurrenciesAdapter(
    private val currencies: List<Currency>,
    private val holderClickTarget: HolderClickTarget
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
            holderClickTarget
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

    override fun getItemCount(): Int {
        return currencies.count()
    }


}