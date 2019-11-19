package com.smlnskgmail.jaman.cryptotracker.api

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File

class HttpClient(
    private val context: Context
) {

    private val cache = Cache(
        File(context.cacheDir, "http"),
        10 * 1024 * 1024
    )

    fun withLocalCache(isOnlinePredicate: () -> Boolean): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(
                cache
            )
            .addInterceptor {
                val request = it.request()
                if (!isOnlinePredicate()) {
                    it.request().newBuilder().addHeader(
                        "Cache-control",
                        "public, only-if-cached, max-stale=${60 * 60 * 24 * 7}"
                    ).build()
                }
                return@addInterceptor it.proceed(
                    request
                )
            }
            .build()
    }

    private fun isOnline(
        context: Context
    ): Boolean {
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnected!!
    }

}