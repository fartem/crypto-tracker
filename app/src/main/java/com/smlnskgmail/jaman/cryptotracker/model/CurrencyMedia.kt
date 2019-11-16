package com.smlnskgmail.jaman.cryptotracker.model

import com.smlnskgmail.jaman.cryptotracker.R

enum class CurrencyMedia(
    val currencySymbol: String,
    val iconResId: Int
) {

    BTC(
        "BTC",
        R.drawable.ic_btc
    ),
    ETH(
        "ETH",
        R.drawable.ic_eth
    ),
    LTC(
        "LTC",
        R.drawable.ic_ltc
    ),
    BNB(
        "BNB",
        R.drawable.ic_bnb
    ),
    EOS(
        "EOS",
        R.drawable.ic_eos
    ),
    NEO(
        "NEO",
        R.drawable.ic_neo
    ),
    DASH(
        "DASH",
        R.drawable.ic_dash
    ),
    ETC(
        "ETC",
        R.drawable.ic_etc
    ),
    BAT(
        "BAT",
        R.drawable.ic_bat
    ),
    MKR(
        "MKR",
        R.drawable.ic_mkr
    ),
    HT(
        "HT",
        R.drawable.ic_ht
    ),
    BTM(
        "BTM",
        R.drawable.ic_btm
    ),
    DAI(
        "DAI",
        R.drawable.ic_dai
    ),
    XVG(
        "XVG",
        R.drawable.ic_xvg
    ),
    AE(
        "AE",
        R.drawable.ic_ae
    ),
    Undefined(
        "",
        R.drawable.ic_error_outline
    );

    companion object {

        fun mediaForCurrency(
            currency: Currency
        ): CurrencyMedia {
            values().forEach {
                if (currency.symbol == it.currencySymbol) {
                    return it
                }
            }
            return Undefined
        }

    }

}