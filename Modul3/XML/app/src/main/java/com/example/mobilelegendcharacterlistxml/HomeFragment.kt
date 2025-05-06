package com.example.mobilelegendcharacterlistxml

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobilelegendcharacterlistxml.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var characterAdapter: HeroMLAdapter
    private val list = ArrayList<HeroML>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        list.clear()
        list.addAll(getListHeroML())
        setupRecyclerView()

        return binding.root

    }

    private fun setupRecyclerView() {
        characterAdapter = HeroMLAdapter(
            list,
            onDetailClick = { url ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            },
            onPenjelasanClick = { name, image, description ->
                val detailFragment = DetailFragment().apply {
                    arguments = Bundle().apply {
                        putString("EXTRA_NAME", name)
                        putInt("EXTRA_PHOTO", image)
                        putString("EXTRA_DESCRIPTION", description)
                    }
                }

                parentFragmentManager.beginTransaction()
                    .replace(R.id.frame_container, detailFragment)
                    .addToBackStack(null)
                    .commit()
            }
        )

        binding.rvCharacter.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = characterAdapter
            setHasFixedSize(true)
        }
    }

    private fun getListHeroML(): ArrayList<HeroML> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataLink = resources.getStringArray(R.array.data_link)
        val dataDesc = resources.getStringArray(R.array.data_desc)
        val listCharacterML = ArrayList<HeroML>()
        for (i in dataName.indices) {
            val character = HeroML(dataName[i],dataPhoto.getResourceId(i, -1),dataLink[i],dataDesc[i])
            listCharacterML.add(character)
        }
        dataPhoto.recycle()
        return listCharacterML
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}