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

    private val viewModel: CharactersViewModel by viewModel()

    lateinit var charactersAdapter: CharacterAdapter

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
        charactersAdapter = CharacterAdapter(this::onClicker)
        setCharactersRV()
        observe()
    }



    private fun setCharactersRV() = with(binding.characterRv) {
        adapter = charactersAdapter
        layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    private fun observe() {
        viewModel.giveCharacters().observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    Toast.makeText(requireContext(), it.massage, Toast.LENGTH_LONG).show()
                }

                is Resource.Loading -> {}

                is Resource.Success -> {
                    charactersAdapter.submitList(it.data)

                }
            }
            binding.animLoading.isVisible = it is Resource.Loading
        }
    }

    private fun onClicker(character: String){
        findNavController().navigate(CharactersListFragmentDirections.actionCharactersListFragmentToCharacterDetailFragment(character))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}