package com.smlnskgmail.jaman.cryptotracker.currencies.impl.cache

import android.content.Context
import com.smlnskgmail.jaman.cryptotracker.currencies.api.Currency
import com.smlnskgmail.jaman.cryptotracker.currencies.api.CurrencyCache
import org.mapdb.DBMaker
import org.mapdb.Serializer
import java.io.File

class MapDbCurrencyCache(
    context: Context,
    serializer: Serializer<Currency>
) : CurrencyCache {

    private val cache = DBMaker
        .fileDB(
            File(
                context.cacheDir.path + "/mdb_cache.db"
            )
        )
        .closeOnJvmShutdown()
        .make()

    private val currencies = cache.treeSet<Currency>(
        "currencies",
        serializer
    )

    override fun putCurrencies(currencies: List<Currency>) {
        this.currencies.addAll(currencies)
        cache.commit()
    }

    override fun updateCurrency(currency: Currency) {
        currencies.remove(currency)
        currencies.add(currency)
    }

    override fun getCurrencies(): List<Currency> {
        return currencies.toList()
    }

    override fun clear() {
        currencies.clear()
    }

}
