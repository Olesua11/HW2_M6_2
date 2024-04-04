package com.example.hw2_m6_2
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.hw2_m6_2.data.CartoonApiService
import com.example.hw2_m6_2.data.Character
import com.example.hw2_m6_2.di.Resource

class CharactersRepository(private val api: CartoonApiService) {

    fun getCharacter(): LiveData<Resource<List<Character>>> =
        BaseRepository.performNetworkRequest { api.getCharacters() }.map { resource ->
            when (resource) {
                is Resource.Success -> Resource.Success(resource.data?.results ?: emptyList())
                is Resource.Loading -> Resource.Loading()
                is Resource.Error -> Resource.Error(resource.massage ?: " error")
            }
        }
}
