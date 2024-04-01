package com.example.hw2_m6_2.character

import androidx.lifecycle.ViewModel
import com.example.hw2_m6_2.Repository
import com.example.hw2_m6_2.di.Resource
import com.example.hw2_m6_2.data.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class CharacterViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val characters = repository.fetchCharacters()

}
