package com.example.hw2_m6_2.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CartoonApiService {

    @GET("character")
    suspend fun getCharacters(): Response<CharacterResponse>

    @GET("character/{id}")
    suspend fun getSingleCharacter(@Path("id") id: Int): Response<Character>

}