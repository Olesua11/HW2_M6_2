package com.example.hw2_m6_2.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.hw2_m6_2.CharacterRepository
import com.example.hw2_m6_2.data.Character
import com.example.hw2_m6_2.di.Resource

class DetailsViewModel (private val repository: CharacterRepository) : ViewModel() {

    suspend fun getCharacter(id: Int): LiveData<Resource<Character>>  = repository.getCharacterUrl(id)

}