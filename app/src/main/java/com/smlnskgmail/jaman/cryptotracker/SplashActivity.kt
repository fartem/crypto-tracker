package com.smlnskgmail.jaman.cryptotracker

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(
            savedInstanceState
        )
        setContentView(
            R.layout.activity_splash
        )
        showLogo()
    }

    private fun showLogo() {
        Handler().postDelayed(
            {
                val appIntent = Intent(
                    this,
                    MainActivity::class.java
                )
                startActivity(
                    appIntent
                )
                finish()
            },
            1500
        )
    }

}