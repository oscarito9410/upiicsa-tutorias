package com.aboolean.tutorias.base

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.aboolean.tutorias.di.ApplicationModule
import com.aboolean.tutorias.di.NetworkModule

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TutoriasApplication : Application() {
    override fun onCreate() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate()
        //Start koin modules
        startKoin {
            androidContext(this@TutoriasApplication)
            modules(listOf(ApplicationModule, NetworkModule))
        }
    }
}