package com.example.pillertest2_2.screen.details.logic

sealed interface DetailsState {
    data object Init             : DetailsState
    data object Loading          : DetailsState
    data object DetailsAvailable : DetailsState

    data class Error(val message :String?) : DetailsState
}
