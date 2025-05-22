package com.example.mobilelegendcharacterlistxml.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mobilelegendcharacterlistxml.data.HeroData
import com.example.mobilelegendcharacterlistxml.model.HeroML
import timber.log.Timber

class HeroViewModel(application: Application) : AndroidViewModel(application) {

    private val _heroList = MutableLiveData<List<HeroML>>()
    val heroList: LiveData<List<HeroML>> = _heroList

    init {
        loadHeroData()
        Timber.d("HeroViewModel initialized")
    }

    private fun loadHeroData() {
        _heroList.value = HeroData.getHeroList(getApplication())
        Timber.d("Data loaded: ${_heroList.value}")
    }

    fun logHeroClick(name: String) {
        Timber.d("Klik hero: $name")
    }
}
