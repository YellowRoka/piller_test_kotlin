package com.example.pillertest2_2.screen.splash.logic

sealed interface SplashUIState{
    data object Init: SplashUIState

    data class VideoLink(val link: Int) : SplashUIState
}
