package com.example.mobilelegendcharacterlistxml

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilelegendcharacterlistxml.databinding.ItemCharMlBinding

class HeroMLAdapter(
    private val listHero: ArrayList<HeroML>,
    private val onDetailClick: (String) -> Unit,
    private val onPenjelasanClick: (String, Int, String) -> Unit
) : RecyclerView.Adapter<HeroMLAdapter.ListViewHolder>() {

    inner class ListViewHolder(val binding: ItemCharMlBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: HeroML) {
            binding.tvItemName.text = character.name
            binding.imgItemMl.setImageResource(character.image)
            binding.tvIsi.text = character.description

            binding.btnDescription.setOnClickListener {
                onDetailClick(character.url)
            }

            binding.btnPenjelasan.setOnClickListener {
                onPenjelasanClick(character.name, character.image, character.description)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemCharMlBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listHero.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listHero[position])
    }
}