package com.example.hw2_m6_2.character
import androidx.core.view.isVisible
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import com.example.hw2_m6_2.BaseFragment
import com.example.hw2_m6_2.R
import com.example.hw2_m6_2.databinding.FragmentCharacterDetailBinding
import com.example.hw2_m6_2.di.Resource
import com.example.hw2_m6_2.di.loadImage
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class CharacterDetailFragment : BaseFragment<
        FragmentCharacterDetailBinding, DetailsViewModel>(FragmentCharacterDetailBinding::inflate) {

    override val viewModel: DetailsViewModel by viewModel()
    override fun observe() {
        super.observe()
        val args: CharacterDetailFragmentArgs by navArgs()
        val characterId = args.characterId

        viewModel.viewModelScope.launch {
            viewModel.getCharacter(characterId).stataHandler(
                success = { result ->
                    binding.apply {

                        tvCharacterName.text = result.name
                        tvStatus.text = result.status
                        tvGender.text = result.gender
                        tvType.text = result.species
                        ivCharacter.loadImage(result.image)
                        tvLocation.text = result.location.name
                        tvFirstSeen.text = result.origin.name

                        val dot = when (result.status) {
                            "Alive" -> R.drawable.ic_green_dot
                            "Dead" -> R.drawable.ic_red_dot
                            else -> R.drawable.ic_grey_dot
                        }
                        dotIndicator.setImageResource(dot)
                    }
                },
                state = {
                    binding.animLoading.isVisible = it is Resource.Loading
                    binding.appBarLayout.isVisible = it !is Resource.Loading
                }
            )
        }
    }
}