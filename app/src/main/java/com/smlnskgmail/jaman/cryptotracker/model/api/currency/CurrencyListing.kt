package com.smlnskgmail.jaman.cryptotracker.model.api.currency

interface CurrencyListing {

    fun currentPrice(): CurrencyPrice
    fun changeDay(): CurrencyPricePercentChangeDay

}
