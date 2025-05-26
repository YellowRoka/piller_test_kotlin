package com.example.pillertest2_2.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val USERNAME_KEY = stringPreferencesKey("username")
val PASSWORD_KEY = stringPreferencesKey("password")

class UserRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
){
    suspend fun login(user: String, password: String) {
        if(user != "Piller"){
            throw Exception("User not exist!")
        }
        if(password != "PillerPassword"){
            throw Exception("Wrong password!")
        }
        try{
            saveUserData(user,password)
        }catch (e: Exception){
            throw Exception("Error in login precess!")
        }

    }

    private suspend fun saveUserData(user: String, password: String) {
        dataStore.edit {
            preferences ->
                preferences[USERNAME_KEY] = user
                preferences[PASSWORD_KEY] = password
        }
    }

    val userNameFlow = dataStore.data.map { 
        preferences -> preferences[USERNAME_KEY] ?: ""
    }

    val passwordFlow = dataStore.data.map {
        preferences ->preferences[PASSWORD_KEY] ?: ""
    }

    suspend fun logOut(){
        dataStore.edit {
            preferences -> preferences.clear()
        }
    }
}
