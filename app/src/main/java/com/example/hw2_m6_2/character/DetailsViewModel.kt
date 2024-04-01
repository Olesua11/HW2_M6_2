package com.example.hw2_m6_2.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.hw2_m6_2.Repository
import com.example.hw2_m6_2.data.Character
import com.example.hw2_m6_2.di.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    fun getCharacter(url: String): LiveData<Resource<Character>> = repository.getCharacterUrl(url)

}
