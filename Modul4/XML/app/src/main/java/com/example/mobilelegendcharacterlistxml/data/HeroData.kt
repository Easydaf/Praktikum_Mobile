package com.example.mobilelegendcharacterlistxml.data

import android.content.Context
import com.example.mobilelegendcharacterlistxml.R
import com.example.mobilelegendcharacterlistxml.model.HeroML

object HeroData {

    fun getHeroList(context: Context): List<HeroML> {
        val names = context.resources.getStringArray(R.array.data_name)
        val descriptions = context.resources.getStringArray(R.array.data_desc)
        val links = context.resources.getStringArray(R.array.data_link)

        // Karena drawable tidak bisa diakses lewat string-array langsung, kita hardcode
        val images = arrayOf(
            R.drawable.hero1,
            R.drawable.hero2,
            R.drawable.hero3,
            R.drawable.hero4,
            R.drawable.hero5,
            R.drawable.hero6
        )

        val list = ArrayList<HeroML>()
        for (i in names.indices) {
            val hero = HeroML(
                name = names[i],
                image = images[i],
                url = links[i],
                description = descriptions[i]
            )
            list.add(hero)
        }
        return list
    }
}