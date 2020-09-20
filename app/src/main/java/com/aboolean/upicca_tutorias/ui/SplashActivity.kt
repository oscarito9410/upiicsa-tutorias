package com.aboolean.upicca_tutorias.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.aboolean.upicca_tutorias.R
import com.aboolean.upicca_tutorias.ui.home.HomeActivity
import com.aboolean.upicca_tutorias.ui.signin.SiginInActivity


class SplashActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        sharedPreferences =
            this.getSharedPreferences(getString(R.string.prefs_name_tutorias), Context.MODE_PRIVATE)


        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(
                Intent(
                    this@SplashActivity,
                    if (sharedPreferences.getString(
                            "idValue",
                            null
                        ) != null
                    )    HomeActivity::class.java else SiginInActivity::class.java
                )
            )
            finish()
        }, DELAY)


    }

    companion object {
        private const val DELAY: Long = 3000
    }


}