package com.example.mobilelegendcharacterlistxml.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HeroViewModelFactory(
    private val application: Application
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HeroViewModel::class.java)) {
            return HeroViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
