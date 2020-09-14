package com.example.upicca_tutorias.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.upicca_tutorias.R
import com.example.upicca_tutorias.ui.home.HomeActivity
import com.example.upicca_tutorias.ui.signin.SiginInActivity
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext


class SplashActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        sharedPreferences =   this.getSharedPreferences(getString(R.string.prefs_name_tutorias), Context.MODE_PRIVATE)

        if (sharedPreferences.getString("idValue", null) != null) {
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
                finish()
            }, DELAY)
        } else {
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this@SplashActivity, SiginInActivity::class.java))
                finish()
            }, DELAY)
        }


    }

    companion object {
        private const val DELAY: Long = 3000
    }


}