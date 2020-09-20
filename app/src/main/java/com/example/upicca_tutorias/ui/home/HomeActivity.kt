package com.example.upicca_tutorias.ui.home

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.upicca_tutorias.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sigin_in.*


class HomeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    /*    if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment, TeachersRegistryFragment()).commit();
        }*/

        setUpNavigation()

    }


    fun visibleBottomView(){
        bottomNavigationView.visibility = View.VISIBLE
    }

    fun goneBottomView(){
        bottomNavigationView.visibility = View.GONE
    }


    private fun setUpNavigation() {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment)?.let { nav ->
            NavigationUI.setupWithNavController(bottomNavigationView, nav.navController)
            nav.navController.navigate(R.id.teachersRegistryFragment)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }


}