package com.example.pillertest2_2.screen.details.logic

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pillertest2_2.data.chache.CharacterCache
import com.example.pillertest2_2.data.model.CharacterModel
import com.example.pillertest2_2.data.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val characterCache: CharacterCache,
    private val characterRepository: CharacterRepository,
) : ViewModel(){

    var selectedCharacterDetails by mutableStateOf<CharacterModel?>(null)
        private set

    var detailsState = MutableStateFlow<DetailsState>(DetailsState.Init)

    init {

        val selectedID = characterCache.selectedCharacterId.value
        viewModelScope.launch {
            detailsState.emit(DetailsState.Loading)
            try {
                if (selectedID != null){
                    val resultData = characterRepository.getCharacterById(selectedID)
                    selectedCharacterDetails = resultData.getOrNull()
                    detailsState.emit(DetailsState.DetailsAvailable)
                }
                else{
                    detailsState.emit(DetailsState.Error("There is no selected character id!"))
                }
            }
            catch (e:Exception){
                detailsState.emit(DetailsState.Error("Something went wrong. Please try again later!"))
            }
        }
    }
}
