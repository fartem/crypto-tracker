package com.smlnskgmail.jaman.cryptotracker

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.smlnskgmail.jaman.cryptotracker.api.CurrencyApi
import com.smlnskgmail.jaman.cryptotracker.api.responses.CurrencyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(
            savedInstanceState
        )
        setContentView(
            R.layout.activity_main
        )

        val coinApi = CurrencyApi()
        coinApi.coinService().currencyList(
            "BTC,ETH,XPC,BCH,USDT,LTC,BNB,EOS,BSV,XLM,TRX,ADA,XMR,LINK,LEO"
        ).enqueue(object : Callback<CurrencyResponse> {
            override fun onFailure(
                call: Call<CurrencyResponse>,
                t: Throwable
            ) {
                Log.e(">>> ", t.printStackTrace().toString())
            }

            override fun onResponse(
                call: Call<CurrencyResponse>,
                response: Response<CurrencyResponse>
            ) {
                Log.e(">>> ", response.body()!!.currencies.size.toString())
            }
        })
    }

    private fun loadCurrencies() {

    }

}
