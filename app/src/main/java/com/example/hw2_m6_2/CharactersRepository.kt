package com.example.hw2_m6_2
import androidx.lifecycle.LiveData
import com.example.hw2_m6_2.data.CartoonApiService
import com.example.hw2_m6_2.data.Character
import com.example.hw2_m6_2.di.Resource

class CharactersRepository (
    private val api: CartoonApiService
): BaseRepository() {

    suspend fun getCharacter(): LiveData<Resource<List<Character>>> = performNetworkRequest {
        api.getCharacters().body()?.results ?: emptyList()
    }
}