package com.example.mobilelegendcharacterlistxml.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilelegendcharacterlistxml.databinding.ItemCharMlBinding
import com.example.mobilelegendcharacterlistxml.model.HeroML

class HeroMLAdapter(
    private val onDetailClick: (String) -> Unit,
    private val onPenjelasanClick: (String, Int, String) -> Unit,
    private val logClick: (String) -> Unit
) : RecyclerView.Adapter<HeroMLAdapter.ListViewHolder>() {

    private val data = ArrayList<HeroML>()

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
                logClick(character.name)
                onPenjelasanClick(character.name, character.image, character.description)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemCharMlBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun submitList(list: List<HeroML>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }
}