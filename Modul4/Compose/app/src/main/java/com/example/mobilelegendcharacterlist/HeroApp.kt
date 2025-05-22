package com.example.mobilelegendcharacterlist

import android.app.Application
import timber.log.Timber

class HeroApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}