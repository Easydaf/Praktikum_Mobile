import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobilelegendcharacterlistxml.adapter.HeroMLAdapter
import com.example.mobilelegendcharacterlistxml.databinding.FragmentHomeBinding
import com.example.mobilelegendcharacterlistxml.ui.DetailFragment
import com.example.mobilelegendcharacterlistxml.viewmodel.HeroViewModel
import com.example.mobilelegendcharacterlistxml.viewmodel.HeroViewModelFactory
import com.example.mobilelegendcharacterlistxml.R

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HeroViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = HeroViewModelFactory(requireActivity().application)
        viewModel = ViewModelProvider(this, factory)[HeroViewModel::class.java]

        val adapter = HeroMLAdapter(
            onDetailClick = { url ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            },
            onPenjelasanClick = { name, image, desc ->
                val bundle = Bundle().apply {
                    putString("name", name)
                    putInt("image", image)
                    putString("desc", desc)
                }

                val detailFragment = DetailFragment().apply {
                    arguments = bundle
                }

                parentFragmentManager.beginTransaction()
                    .replace(R.id.frame_container, detailFragment)
                    .addToBackStack(null)
                    .commit()
            },
            logClick = { name ->
                viewModel.logHeroClick(name)
            }
        )


        binding.rvCharacter.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCharacter.adapter = adapter

        viewModel.heroList.observe(viewLifecycleOwner) { heroList ->
            adapter.submitList(heroList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}