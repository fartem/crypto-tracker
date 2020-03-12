package com.smlnskgmail.jaman.cryptotracker.view.list

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.smlnskgmail.jaman.cryptotracker.App
import com.smlnskgmail.jaman.cryptotracker.R
import com.smlnskgmail.jaman.cryptotracker.components.BaseThemeFragment
import com.smlnskgmail.jaman.cryptotracker.model.api.cache.CurrencyCache
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.Currency
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.CurrencyApi
import com.smlnskgmail.jaman.cryptotracker.presenter.list.CurrenciesListPresenter
import com.smlnskgmail.jaman.cryptotracker.presenter.list.CurrenciesListPresenterImpl
import com.smlnskgmail.jaman.cryptotracker.view.info.BottomSheetCurrencyInfo
import com.smlnskgmail.jaman.cryptotracker.view.list.recycler.CurrenciesAdapter
import com.smlnskgmail.jaman.cryptotracker.view.list.recycler.CurrencyHolder
import kotlinx.android.synthetic.main.fragment_currencies_list.*
import javax.inject.Inject

class CurrenciesListFragment : BaseThemeFragment(), CurrenciesListView {

    private lateinit var currenciesListPresenter: CurrenciesListPresenter

    @Inject
    lateinit var currencyApi: CurrencyApi

    @Inject
    lateinit var currencyCache: CurrencyCache

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        App.applicationComponent.inject(this)
        configureCurrenciesList()
        configureSwipeRefresh()
        currenciesListPresenter = CurrenciesListPresenterImpl()
        currenciesListPresenter.init(
            this,
            currencyApi,
            currencyCache
        )
    }

    private fun configureSwipeRefresh() {
        currencies_list_refresh.isEnabled = false
        currencies_list_refresh.setColorSchemeColors(
            ContextCompat.getColor(
                context!!,
                R.color.colorAccent
            )
        )
        currencies_list_refresh.setOnRefreshListener {
            currenciesListPresenter.refreshCurrencies()
        }
    }

    private fun configureCurrenciesList() {
        currencies_list.messageView = list_empty_message
    }

    override fun showCurrencies(
        currencies: List<Currency>
    ) {
        currencies_list.adapter = CurrenciesAdapter(
            currencies.sortedBy { it.currencyType().internalPosition },
            currencyClickTarget(),
            priceRefreshTarget()
        )
        currencies_list_refresh.isEnabled = true
        currencies_list_refresh.isRefreshing = false
    }

    private fun currencyClickTarget(): CurrencyHolder.CurrencyClickTarget {
        return object : CurrencyHolder.CurrencyClickTarget {
            override fun onCurrencyClick(currency: Currency) {
                currenciesListPresenter.currencySelected(
                    currency
                )
            }
        }
    }

    private fun priceRefreshTarget(): CurrencyHolder.CurrencyRefreshClickTarget {
        return object : CurrencyHolder.CurrencyRefreshClickTarget {
            override fun onCurrencyRefreshClick(currency: Currency) {
                currenciesListPresenter.updateCurrencyListingFor(
                    currency
                )
            }
        }
    }

    override fun showFullScreenLoader() {
        list_progress_bar.visibility = View.VISIBLE
    }

    override fun showSeekLoader() {
        top_list_progress_bar.visibility = View.VISIBLE
    }

    override fun hideFullScreenLoader() {
        list_progress_bar.visibility = View.GONE
    }

    override fun hideSeekLoader() {
        top_list_progress_bar.visibility = View.GONE
    }

    override fun showLoadError() {
        showSnackbar(
            getString(R.string.currency_load_error_message)
        )
    }

    override fun showCurrencyInfo(
        currency: Currency
    ) {
        val arguments = Bundle()
        arguments.putSerializable(
            "currency",
            currency
        )
        val bottomSheetCurrencyInfo = BottomSheetCurrencyInfo()
        bottomSheetCurrencyInfo.arguments = arguments
        bottomSheetCurrencyInfo.show(
            activity!!.supportFragmentManager,
            BottomSheetCurrencyInfo::class.java.canonicalName
        )
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(
            currencies_list_root,
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun updateCurrency(currency: Currency) {
        (currencies_list.adapter!! as CurrenciesAdapter).refreshCurrency(currency)
    }

    override fun layoutResId(): Int {
        return R.layout.fragment_currencies_list
    }

}
