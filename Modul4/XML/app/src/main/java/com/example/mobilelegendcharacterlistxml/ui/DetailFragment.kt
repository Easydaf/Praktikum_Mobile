package com.example.mobilelegendcharacterlistxml.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mobilelegendcharacterlistxml.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        val name = arguments?.getString("name")
        val photo = arguments?.getInt("image")
        val description = arguments?.getString("desc")

        binding.tvItemName.text = name
        photo?.let {
            binding.imgItemMl.setImageResource(it)
        }
        binding.tvIsi.text = description

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}