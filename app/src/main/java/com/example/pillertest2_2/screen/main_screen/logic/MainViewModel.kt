package com.example.pillertest2_2.screen.main_screen.logic

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pillertest2_2.data.chache.CharacterCache
import com.example.pillertest2_2.data.model.CharacterModel
import com.example.pillertest2_2.data.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val characterRepository: CharacterRepository,
    private val characterCache: CharacterCache,
) : ViewModel(){

    var dataList by mutableStateOf<List<CharacterModel>?>(null)
        private set

    var favoritesMap by mutableStateOf<Map<Int, CharacterModel>?>(null)
        private set

    var isFavoritesActive by mutableStateOf<Boolean>(false)
        private set

    var searchedName by mutableStateOf<String>("")
        private set

    var mainState = MutableStateFlow<MainState>(MainState.Init)
    private val _event = Channel<MainEvent>()
    val event = _event.receiveAsFlow()

    init {
        getAllData()
    }

    fun refresh(){
        getAllData()
    }

    private fun getAllData(){
        viewModelScope.launch{
            mainState.emit(MainState.Loading)
            try {
                val result = characterRepository.getCharacters()
                val data = result.getOrNull()
                if(data == null){
                    mainState.emit(MainState.Error("There is no Character!"))
                    _event.send(MainEvent.GeneralError("There is no Character!"))
                }
                else {
                    favoritesMap = characterCache.favoritesMap.value
                    data.results!!.forEach {
                        it.isFavorite = favoritesMap?.get(it.id)?.isFavorite ?: false

                    }
                    dataList = data.results
                    mainState.emit(MainState.Success)
                }
            }
            catch (e: Exception){
                mainState.emit(MainState.Error(e.message.toString()))
                _event.send( MainEvent.GeneralError(e.message.toString()))
            }
        }
    }

    fun searchById(id:Int){
        viewModelScope.launch {
            try{
                characterCache.updateSelectedCharacterId(id)
                _event.send( MainEvent.OpenDetails )
            }
            catch (e: Exception){
                mainState.emit(MainState.Error(e.message.toString()))
                _event.send( MainEvent.GeneralError(e.message.toString()))
            }
        }
    }

    fun searchByName(name : String){
        searchedName = name
        viewModelScope.launch {
            try{
                mainState.emit(MainState.Loading)
                val result = characterRepository.getCharactersByName(name)
                val data = result.getOrNull()
                if(data == null){
                    dataList = emptyList()
                    mainState.emit(MainState.NoCharacter)
                }
                else{
                    dataList = data.results
                    favoritesMap = characterCache.favoritesMap.value
                    dataList     = dataList?.map {
                        it.copy(isFavorite = favoritesMap?.get(it.id)?.isFavorite ?: false)
                    }
                    mainState.emit(MainState.Success)
                }
            }
            catch (e: Exception){
                mainState.emit(MainState.Error(e.message.toString()))
                _event.send(MainEvent.GeneralError(e.message.toString()))
            }
        }
    }

    fun changeFavoriteState(character:CharacterModel){
        characterCache.changeFavorites(character)
        favoritesMap = characterCache.favoritesMap.value
        dataList     = dataList?.map {
            it.copy(isFavorite = favoritesMap?.get(it.id)?.isFavorite ?: false)
        }
    }

    fun showFavorites(isActive: Boolean) {
        viewModelScope.launch {
            isFavoritesActive = isActive
            if(isFavoritesActive){
                mainState.emit(MainState.Favorites)
            }
            else{
                mainState.emit(MainState.Success)
            }
        }
    }
}
