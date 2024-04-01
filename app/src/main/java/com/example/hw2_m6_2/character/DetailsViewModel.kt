package com.example.hw2_m6_2.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hw2_m6_2.Repository
import com.example.hw2_m6_2.data.Character
import com.example.hw2_m6_2.di.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private var _characters = MutableLiveData<Resource<Character>>()
    val characters: LiveData<Resource<Character>> = _characters

    fun getData(id: Int): LiveData<Resource<Character>> = repository.getCharacter(id)

    fun getCharacter(url: String): LiveData<Resource<Character>>  = repository.getCharacterUrl(url)

}