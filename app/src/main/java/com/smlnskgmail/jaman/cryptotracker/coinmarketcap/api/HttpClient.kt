package com.smlnskgmail.jaman.cryptotracker.coinmarketcap.api

import android.content.Context
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File

class HttpClient(
    context: Context,
    cacheSize: Long = 10 * 1024 * 1024,
    private val cacheAge: Long = 60 * 60 * 24 * 7
) {

    private val cache = Cache(
        File(
            context.cacheDir,
            "http"
        ),
        cacheSize
    )

    fun withLocalCache(cachePredicate: () -> Boolean): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor {
                val request = it.request()
                if (!cachePredicate()) {
                    it.request().newBuilder().addHeader(
                        "Cache-control",
                        "public, only-if-cached, max-stale=$cacheAge"
                    ).build()
                }
                return@addInterceptor it.proceed(request)
            }
            .build()
    }

}