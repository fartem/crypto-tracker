package com.smlnskgmail.jaman.cryptotracker.currencies.api

import com.smlnskgmail.jaman.cryptotracker.R

@Suppress(
    "unused",
    "MagicNumber"
)
enum class CurrencyType(
    val internalPosition: Int,
    val currencySymbol: String,
    val iconResId: Int,
    val accentColor: String,
    val site: String
) {

    BTC(
        1,
        "BTC",
        R.drawable.ic_btc,
        "#F7931A",
        "https://www.bitcoin.com/"
    ),
    ETH(
        2,
        "ETH",
        R.drawable.ic_eth,
        "#627EEA",
        "https://ethereum.org/"
    ),
    LTC(
        3,
        "LTC",
        R.drawable.ic_ltc,
        "#BFBBBB",
        "https://litecoin.org/"
    ),
    BNB(
        4,
        "BNB",
        R.drawable.ic_bnb,
        "#F3BA2F",
        "https://www.binance.com/"
    ),
    EOS(
        5,
        "EOS",
        R.drawable.ic_eos,
        "#000000",
        "https://eos.io/"
    ),
    NEO(
        6,
        "NEO",
        R.drawable.ic_neo,
        "#58BF00",
        "https://neo.org/"
    ),
    DASH(
        7,
        "DASH",
        R.drawable.ic_dash,
        "#008CE7",
        "https://www.dash.org/"
    ),
    ETC(
        8,
        "ETC",
        R.drawable.ic_etc,
        "#328332",
        "https://ethereumclassic.org/"
    ),
    BAT(
        9,
        "BAT",
        R.drawable.ic_bat,
        "#FF5000",
        "https://basicattentiontoken.org/"
    ),
    MKR(
        10,
        "MKR",
        R.drawable.ic_mkr,
        "#1abc9c",
        "https://makerdao.com/"
    ),
    HT(
        11,
        "HT",
        R.drawable.ic_ht,
        "#2A3069",
        "https://www.hbg.com/"
    ),
    BTM(
        12,
        "BTM",
        R.drawable.ic_btm,
        "#504C4C",
        "http://bytom.io/"
    ),
    DAI(
        13,
        "DAI",
        R.drawable.ic_dai,
        "#B68900",
        "http://www.makerdao.com/"
    ),
    XVG(
        14,
        "XVG",
        R.drawable.ic_xvg,
        "#00CBFF",
        "http://vergecurrency.com/"
    ),
    AE(
        15,
        "AE",
        R.drawable.ic_ae,
        "#DE3F6B",
        "https://www.aeternity.com/"
    ),
    MCO(
        16,
        "MCO",
        R.drawable.ic_mco,
        "#103F68",
        "https://crypto.com/"
    ),
    ZIL(
        1,
        "ZIL",
        R.drawable.ic_zil,
        "#49c1bf",
        "https://www.zilliqa.com/"
    ),
    ELA(
        18,
        "ELA",
        R.drawable.ic_ela,
        "#3FBADF",
        "https://www.elastos.org/"
    ),
    IOTX(
        19,
        "IOTX",
        R.drawable.ic_iotx,
        "#00D4D5",
        "https://iotex.io/"
    ),
    TKN(
        20,
        "TKN",
        R.drawable.ic_tkn,
        "#24DD7B",
        "https://monolith.xyz/"
    ),
    PLR(
        21,
        "PLR",
        R.drawable.ic_plr,
        "#00bfff",
        "https://pillarproject.io/"
    ),
    MED(
        22,
        "MED",
        R.drawable.ic_med,
        "#00B0FF",
        "https://medibloc.org/"
    ),
    MDA(
        23,
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
                if (currency.symbol() == it.currencySymbol) {
                    return it
                }
            }
            return null
        }

    }

}
