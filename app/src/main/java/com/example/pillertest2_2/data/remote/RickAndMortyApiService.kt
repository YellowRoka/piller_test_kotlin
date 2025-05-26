package com.example.pillertest2_2.data.remote

import com.example.pillertest2_2.data.model.CharacterModel
import com.example.pillertest2_2.data.model.CharacterResponse
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApiService {

    @GET("character")
    suspend fun getCharacters() : Response<CharacterResponse?>

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Response<CharacterModel>

    @GET("character")
    suspend fun getCharactersByName(@Query("name") name:String): Response<CharacterResponse>
}
