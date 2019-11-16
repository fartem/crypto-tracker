package com.smlnskgmail.jaman.cryptotracker

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.smlnskgmail.jaman.cryptotracker.api.CurrencyApi
import com.smlnskgmail.jaman.cryptotracker.api.responses.CurrencyResponse
import com.smlnskgmail.jaman.cryptotracker.list.CurrenciesAdapter
import com.smlnskgmail.jaman.cryptotracker.list.holder.HolderClickTarget
import com.smlnskgmail.jaman.cryptotracker.list.info.BottomSheetCurrencyInfo
import com.smlnskgmail.jaman.cryptotracker.model.Currency
import com.smlnskgmail.jaman.cryptotracker.model.CurrencyMedia
import com.smlnskgmail.jaman.cryptotracker.preferences.PreferencesManager
import com.smlnskgmail.jaman.cryptotracker.preferences.theme.Theme
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
        setTheme(
            PreferencesManager.theme(
                this
            ).themeResId
        )
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
            CurrencyMedia.supportedSymbols().joinToString(
                ","
            ).dropLast(
                1
            )
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
                    val body = response.body()
                    if (body == null) {
                        createList(
                            emptyList()
                        )
                    } else {
                        createList(
                            body.currencies
                        )
                    }
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

    override fun holderItemClick(
        currency: Currency
    ) {
        val bundle = Bundle()
        bundle.putSerializable(
            "currency",
            currency
        )

        val bottomSheetCurrencyInfo = BottomSheetCurrencyInfo()
        bottomSheetCurrencyInfo.arguments = bundle
        bottomSheetCurrencyInfo.show(
            supportFragmentManager,
            BottomSheetCurrencyInfo::class.java.canonicalName
        )
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
        val newTheme = if (PreferencesManager.theme(
                this
            ) == Theme.Light) {
            Theme.Dark
        } else {
            Theme.Light
        }

        PreferencesManager.changeTheme(
            this,
            newTheme
        )

        val restartIntent = Intent(
            this,
            MainActivity::class.java
        )
        finish()
        startActivity(
            restartIntent
        )
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
