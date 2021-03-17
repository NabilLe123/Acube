package com.app.acube

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.app.acube.auth.SignInActivity
import com.app.acube.helper.AuthSharedPref

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val authSharedPref = AuthSharedPref(this)

        Handler(Looper.getMainLooper()).postDelayed({
            if (authSharedPref.getEmployeeCode() == -1) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        }, 2000)
    }
}