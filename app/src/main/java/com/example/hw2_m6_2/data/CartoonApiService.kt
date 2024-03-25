package com.example.hw2_m6_2.data

import retrofit2.http.GET

interface CartoonApiService {

    @GET("character")
    suspend fun getCharacters(): CharacterResponse

}