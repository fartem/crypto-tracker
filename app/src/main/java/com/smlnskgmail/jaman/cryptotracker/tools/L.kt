package com.smlnskgmail.jaman.cryptotracker.tools

import android.util.Log
import com.smlnskgmail.jaman.cryptotracker.BuildConfig

object L {

    fun e(throwable: Throwable) {
        if (BuildConfig.DEBUG) {
            Log.e(
                "DEBUG",
                "---",
                throwable
            )
        }
    }

}
