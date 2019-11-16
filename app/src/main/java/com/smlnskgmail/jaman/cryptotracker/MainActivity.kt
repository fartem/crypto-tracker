package com.smlnskgmail.jaman.cryptotracker

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.smlnskgmail.jaman.cryptotracker.api.CurrencyApi
import com.smlnskgmail.jaman.cryptotracker.api.responses.CurrencyResponse
import com.smlnskgmail.jaman.cryptotracker.list.CurrenciesAdapter
import com.smlnskgmail.jaman.cryptotracker.list.holder.HolderClickTarget
import com.smlnskgmail.jaman.cryptotracker.model.Currency
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_empty_message.*
import kotlinx.android.synthetic.main.list_progress_bar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), HolderClickTarget {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(
            savedInstanceState
        )
        setContentView(
            R.layout.activity_main
        )
        showLoader()
        loadCurrencies()
    }

    private fun showLoader() {
        currencies_list.withMessage(
            list_empty_message
        )
        currencies_list.withProgressBar(
            list_progress_bar
        )
    }

    private fun loadCurrencies() {
        CurrencyApi.currencyService().currencyList(
            "BTC,ETH,LTC,BNB,EOS,NEO,DASH,ETC,BAT,MKR,HT,BTM,DAI,XVG,AE"
        ).enqueue(
            object : Callback<CurrencyResponse> {
                override fun onFailure(
                    call: Call<CurrencyResponse>,
                    t: Throwable
                ) {
                    createList(
                        emptyList()
                    )
                }

                override fun onResponse(
                    call: Call<CurrencyResponse>,
                    response: Response<CurrencyResponse>
                ) {
                    createList(
                        response.body()!!.currencies
                    )
                }
            }
        )
    }

    private fun createList(
        currencies: List<Currency>
    ) {
        currencies_list.adapter = CurrenciesAdapter(
            currencies,
            this
        )
    }

    override fun holderItemClick(currency: Currency) {

    }

    override fun onOptionsItemSelected(
        item: MenuItem
    ): Boolean {
        when (item.itemId) {
            R.id.menu_item_theme_change -> changeAppTheme()
        }
        return super.onOptionsItemSelected(
            item
        )
    }

    private fun changeAppTheme() {

    }

    override fun onPrepareOptionsMenu(
        menu: Menu?
    ): Boolean {
        menuInflater.inflate(
            R.menu.menu_main,
            menu
        )
        return true
    }

}
