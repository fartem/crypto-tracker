package com.smlnskgmail.jaman.cryptotracker.logic.api

import com.smlnskgmail.jaman.cryptotracker.R

@Suppress("unused")
enum class CurrencyType(
    val currencySymbol: String,
    val iconResId: Int,
    val accentColor: String,
    val site: String
) {

    BTC(
        "BTC",
        R.drawable.ic_btc,
        "#F7931A",
        "https://www.bitcoin.com/"
    ),
    ETH(
        "ETH",
        R.drawable.ic_eth,
        "#627EEA",
        "https://ethereum.org/"
    ),
    LTC(
        "LTC",
        R.drawable.ic_ltc,
        "#BFBBBB",
        "https://litecoin.org/"
    ),
    BNB(
        "BNB",
        R.drawable.ic_bnb,
        "#F3BA2F",
        "https://www.binance.com/"
    ),
    EOS(
        "EOS",
        R.drawable.ic_eos,
        "#000000",
        "https://eos.io/"
    ),
    NEO(
        "NEO",
        R.drawable.ic_neo,
        "#58BF00",
        "https://neo.org/"
    ),
    DASH(
        "DASH",
        R.drawable.ic_dash,
        "#008CE7",
        "https://www.dash.org/"
    ),
    ETC(
        "ETC",
        R.drawable.ic_etc,
        "#328332",
        "https://ethereumclassic.org/"
    ),
    BAT(
        "BAT",
        R.drawable.ic_bat,
        "#FF5000",
        "https://basicattentiontoken.org/"
    ),
    MKR(
        "MKR",
        R.drawable.ic_mkr,
        "#1abc9c",
        "https://makerdao.com/"
    ),
    HT(
        "HT",
        R.drawable.ic_ht,
        "#2A3069",
        "https://www.hbg.com/"
    ),
    BTM(
        "BTM",
        R.drawable.ic_btm,
        "#504C4C",
        "http://bytom.io/"
    ),
    DAI(
        "DAI",
        R.drawable.ic_dai,
        "#B68900",
        "http://www.makerdao.com/"
    ),
    XVG(
        "XVG",
        R.drawable.ic_xvg,
        "#00CBFF",
        "http://vergecurrency.com/"
    ),
    AE(
        "AE",
        R.drawable.ic_ae,
        "#DE3F6B",
        "https://www.aeternity.com/"
    ),
    MCO(
        "MCO",
        R.drawable.ic_mco,
        "#103F68",
        "https://crypto.com/"
    ),
    ZIL(
        "ZIL",
        R.drawable.ic_zil,
        "#49c1bf",
        "https://www.zilliqa.com/"
    ),
    ELA(
        "ELA",
        R.drawable.ic_ela,
        "#3FBADF",
        "https://www.elastos.org/"
    ),
    IOTX(
        "IOTX",
        R.drawable.ic_iotx,
        "#00D4D5",
        "https://iotex.io/"
    ),
    TKN(
        "TKN",
        R.drawable.ic_tkn,
        "#24DD7B",
        "https://monolith.xyz/"
    ),
    PLR(
        "PLR",
        R.drawable.ic_plr,
        "#00bfff",
        "https://pillarproject.io/"
    ),
    MED(
        "MED",
        R.drawable.ic_med,
        "#00B0FF",
        "https://medibloc.org/en/"
    ),
    MDA(
        "MDA",
        R.drawable.ic_mda,
        "#01a64f",
        "https://moedaseeds.com/"
    );

    companion object {

        fun supportedSymbols(): List<String> {
            return values().map {
                it.currencySymbol
            }
        }

        fun typeForCurrency(currency: Currency): CurrencyType? {
            values().forEach {
                if (currency.symbol == it.currencySymbol) {
                    return it
                }
            }
            return null
        }

    }

}
