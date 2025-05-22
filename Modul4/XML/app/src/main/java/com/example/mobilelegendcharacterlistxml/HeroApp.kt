package com.example.mobilelegendcharacterlistxml


import android.app.Application
import timber.log.Timber



class HeroApp : Application() {
    override fun onCreate() {
        super.onCreate()
            Timber.plant(Timber.DebugTree())
    }
}