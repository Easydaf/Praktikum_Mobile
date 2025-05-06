package com.example.mobilelegendcharacterlistxml
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HeroML(
    val name: String,
    val image: Int,
    val url: String,
    val description: String
): Parcelable
