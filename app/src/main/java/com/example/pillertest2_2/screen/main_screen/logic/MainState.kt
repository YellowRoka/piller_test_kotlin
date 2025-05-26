package com.example.pillertest2_2.screen.main_screen.logic

sealed interface MainState {
    data object Init       : MainState
    data object Loading    : MainState
    data object Success    : MainState
    data object Favorites  : MainState
    data object NoCharacter: MainState

    data class Error(val message:String?) : MainState
}
