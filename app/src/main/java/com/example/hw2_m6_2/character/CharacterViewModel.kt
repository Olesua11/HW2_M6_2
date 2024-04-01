package com.example.hw2_m6_2.character

import androidx.lifecycle.LiveData
import com.example.hw2_m6_2.data.Character
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hw2_m6_2.Repository
import com.example.hw2_m6_2.di.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private var _characters = MutableLiveData<Resource<List<Character>>>()
    val characters: LiveData<Resource<List<Character>>> = _characters

    fun giveCharacters(): LiveData<Resource<List<Character>>> = repository.fetchCharacters()

}