package com.smlnskgmail.jaman.cryptotracker.model.api.currency

interface CurrencyListing {

    fun currentPrice(): CurrencyPriceValue
    fun changeHour(): CurrencyPriceValue

}
