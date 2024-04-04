package com.example.hw2_m6_2

import androidx.lifecycle.LiveData
import com.example.hw2_m6_2.data.CartoonApiService
import com.example.hw2_m6_2.di.Resource
import com.example.hw2_m6_2.data.Character

class CharacterRepository(private val api: CartoonApiService) {
    fun getCharacterUrl(url: String): LiveData<Resource<Character>> =
        BaseRepository.performNetworkRequest { api.getSingleCharacter(url) }
}
