package com.example.upicca_tutorias.base

import android.app.Application
import com.example.upicca_tutorias.di.ApplicationModule
import com.example.upicca_tutorias.di.NetworkModule

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TutoriasApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //Start koin modules
        startKoin {
            androidContext(this@TutoriasApplication)
            modules(listOf(ApplicationModule, NetworkModule))
        }
    }
}