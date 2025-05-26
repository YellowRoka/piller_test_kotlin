package com.example.pillertest2_2.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.pillertest2_2.data.remote.RickAndMortyApiService
import com.example.pillertest2_2.data.repository.CharacterRepository
import com.example.pillertest2_2.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(dataStore: DataStore<Preferences>): UserRepository{
        return UserRepository(dataStore)
    }

    @Provides
    @Singleton
    fun provideCharacterRepository(apiService : RickAndMortyApiService): CharacterRepository{
        return CharacterRepository(apiService)
    }
}
