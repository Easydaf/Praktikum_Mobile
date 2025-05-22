package com.example.mobilelegendcharacterlist.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mobilelegendcharacterlist.model.DataHero
import com.example.mobilelegendcharacterlist.data.HeroData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber

class HeroViewModel : ViewModel() {

    private val _heroList = MutableStateFlow<List<DataHero>>(emptyList())
    val heroList: StateFlow<List<DataHero>> = _heroList.asStateFlow()


    init {
        loadHeroes()
    }

    private fun loadHeroes() {
        Timber.d("Memuat data hero...")
        _heroList.value = HeroData.heroList
    }

    fun logHeroClick(name: String) {
        Timber.d("Tombol 'Detail' diklik untuk hero: $name")
    }
}
