package com.example.hw2_m6_2.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.hw2_m6_2.CharactersRepository
import com.example.hw2_m6_2.di.Resource
import com.example.hw2_m6_2.data.Character
class CharactersViewModel (
    private val repository: CharactersRepository
): ViewModel() {

    suspend fun giveCharacters(): LiveData<Resource<List<Character>>> = repository.getCharacter()

}