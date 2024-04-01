package com.example.hw2_m6_2.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw2_m6_2.databinding.FragmentCharactersListBinding
import com.example.hw2_m6_2.di.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersListFragment : Fragment() {

    private val viewModel: CharacterViewModel by viewModel()

    private var _binding: FragmentCharactersListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharactersListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val charactersAdapter = CharacterAdapter(this::onClicker)
        binding.characterRv.apply {
            adapter = charactersAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        observe(charactersAdapter)
    }

    private fun observe(adapter: CharacterAdapter) {
        viewModel.characters.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Error -> {
                    Toast.makeText(requireContext(), resource.massage, Toast.LENGTH_LONG).show()
                }
                is Resource.Loading -> {
                    binding.animLoading.isVisible = true
                }
                is Resource.Success -> {
                    adapter.submitList(resource.data)
                    binding.animLoading.isVisible = false
                }
            }
        }
    }

    private fun onClicker(characterUrl: String) {
        findNavController().navigate(CharactersListFragmentDirections.actionCharactersListFragmentToCharacterDetailFragment(characterUrl))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
