package com.example.upicca_tutorias.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.upicca_tutorias.R
import com.example.upicca_tutorias.ui.signin.SiginInActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashActivity, SiginInActivity::class.java))
            finish()
        }, DELAY)
    }

    companion object {
        private const val DELAY: Long = 3000
    }
}