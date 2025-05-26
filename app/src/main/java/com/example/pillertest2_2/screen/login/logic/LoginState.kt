package com.example.pillertest2_2.screen.login.logic

//Csak UI state tárolás olyan esetekre amiket hosszan kell tárolni
sealed interface LoginState{
    data object Init    : LoginState
    data object Loading : LoginState
    data object Success : LoginState

    data class Error( val message : String ) : LoginState
}
