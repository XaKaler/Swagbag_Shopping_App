package com.shopping.swagbag

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class CommonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)

        setSplashScreen()
    }

    private fun sendToNext() {
        Handler().postDelayed({
            startActivity(Intent(this@CommonActivity, MainActivity::class.java))
            finish()
        }, 3000)
    }

    private fun setSplashScreen() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frmlytCommon, SplashScreenFragment())
            .commit()

        sendToNext()
    }
}