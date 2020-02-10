package com.smlnskgmail.jaman.cryptotracker.currencies.api

interface CurrencyListing {

    fun currentPrice(): CurrencyPriceValue
    fun changeHour(): CurrencyPriceValue

}
