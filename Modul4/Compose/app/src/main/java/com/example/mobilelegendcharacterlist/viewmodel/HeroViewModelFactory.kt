package com.example.mobilelegendcharacterlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HeroViewModelFactory(
    private val title: String
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HeroViewModel::class.java)) {
            return HeroViewModel() as T // ganti jika constructor butuh parameter
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
