package com.example.hw2_m6_2.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.hw2_m6_2.R
import com.example.hw2_m6_2.databinding.FragmentCharacterDetailBinding
import com.example.hw2_m6_2.di.Resource
import com.example.hw2_m6_2.di.loadImage
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterDetailFragment : Fragment() {

    private val viewModel: DetailsViewModel by viewModel()

    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCharacterData()
    }

    private fun setCharacterData() {
        val args: CharacterDetailFragmentArgs by navArgs()
        val characterUrl = args.characterId

        viewModel.getCharacter(characterUrl).observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.appBarLayout.visibility = View.GONE
                    binding.animLoading.visibility = View.VISIBLE
                }

                is Resource.Success -> {

                    binding.apply {

                        tvCharacterName.text = result.data!!.name
                        tvStatus.text = result.data.status
                        val species = " - " + result.data.species
                        tvGender.text = result.data.gender
                        tvType.text = species
                        ivCharacter.loadImage(result.data.image)
                        tvLocation.text = result.data.location.name
                        tvFirstSeen.text = result.data.origin.name

                        val dot = when (result.data.status) {
                            "Alive" -> R.drawable.ic_green_dot
                            "Dead" -> R.drawable.ic_red_dot
                            else -> R.drawable.ic_grey_dot
                        }
                        dotIndicator.setImageResource(dot)
                    }
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), result.massage, Toast.LENGTH_LONG).show()
                }
            }
            if (result !is Resource.Loading) {
                binding.appBarLayout.visibility = View.VISIBLE
                binding.animLoading.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
