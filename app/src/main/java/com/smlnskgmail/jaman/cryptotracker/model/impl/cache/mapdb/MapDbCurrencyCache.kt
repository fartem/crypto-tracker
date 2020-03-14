package com.smlnskgmail.jaman.cryptotracker.model.impl.cache.mapdb

import android.content.Context
import com.smlnskgmail.jaman.cryptotracker.model.api.cache.CurrencyCache
import com.smlnskgmail.jaman.cryptotracker.model.api.currency.Currency
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

    private val currencies = cache.hashSet(
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
        cache.commit()
    }

    override fun getCurrencies(): List<Currency> {
        return currencies.toList()
    }

    override fun clear() {
        currencies.clear()
    }

}
