package com.egp.fmkotlin.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.egp.fmkotlin.R
import com.egp.fmkotlin.ui.auth.AuthActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            startActivity(Intent(this@SplashScreenActivity, AuthActivity::class.java))
            finishAffinity()
        }, 3000)
    }
}