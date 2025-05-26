package com.example.pillertest2_2.data.repository

import android.util.Log
import com.example.pillertest2_2.data.model.CharacterModel
import com.example.pillertest2_2.data.model.CharacterResponse
import com.example.pillertest2_2.data.remote.RickAndMortyApiService
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val apiService : RickAndMortyApiService
){
    suspend fun getCharacters(): Result<CharacterResponse?>{
        return try {
            val response = apiService.getCharacters()
            if (response.isSuccessful && response.body() != null){
                Result.success(response.body()!!)
            }
            else{
                Result.failure(Exception(response.message()))
            }
        }
        catch (e:Exception){
            Log.e("REPOSITORY_ERROR", "getCharacters: $e" )
            Result.failure(Exception("Something went wrong! Come back later!"))
        }
    }

    suspend fun getCharacterById(id:Int) : Result<CharacterModel?>{
        return try{
            val response = apiService.getCharacterById(id)
            if(response.isSuccessful && response.body() != null){
                Result.success(response.body()!!)
            }
            else{
                Result.failure(Exception(response.message()))
            }
        }
        catch (e:Exception){
            Log.e("REPOSITORY_ERROR", "getCharacterById: $e" )
            Result.failure(Exception("Something went wrong! Come back later!"))
        }
    }

    suspend fun getCharactersByName(name: String) : Result<CharacterResponse?>{
        return try{
            val response = apiService.getCharactersByName(name)
            if(response.isSuccessful){
                Result.success(response.body())
            }
            else{
                Result.failure(Exception(response.message()))
            }
        }
        catch (e : Exception){
            Log.e("REPOSITORY_ERROR", "getCharactersByName: $e" )
            Result.failure(Exception("Something went wrong! Come back later!"))
        }
    }
}
