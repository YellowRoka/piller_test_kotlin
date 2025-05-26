package com.example.pillertest2_2.data.chache

import com.example.pillertest2_2.data.model.CharacterModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterCache @Inject constructor(){

    private val _characterId = MutableStateFlow<Int?>(null)
    val selectedCharacterId = _characterId.asStateFlow()

    private var _favoriteMap = MutableStateFlow<Map<Int, CharacterModel>>(emptyMap())
    val favoritesMap = _favoriteMap.asStateFlow()

    fun updateSelectedCharacterId(id:Int?){
        _characterId.value = id
    }

    fun changeFavorites(character: CharacterModel) {
        val favoriteMap = _favoriteMap.value

        if( favoriteMap.keys.contains(character.id) ){
            _favoriteMap.value = _favoriteMap.value.minus(character.id!!)
        }
        else{
            _favoriteMap.value = _favoriteMap.value.plus(
                character.id!! to character.copy(isFavorite = true)
            )

        }
    }
}
