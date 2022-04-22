package com.shopping.swagbag.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.shopping.swagbag.R
import com.shopping.swagbag.main_activity.MainActivity

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)

        setSplashScreen()
    }

    private fun sendToNext() {
        Handler().postDelayed({
            startActivity(Intent(this@SplashScreen, MainActivity::class.java))
            finish()
        }, 2000)
    }

    private fun setSplashScreen() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frmlytCommon, SplashScreenFragment())
            .commit()

        sendToNext()
    }
}