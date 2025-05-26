package com.example.pillertest2_2.screen.main_screen.logic

sealed interface MainEvent {
    data object OpenDetails : MainEvent

    data class GeneralError(val message: String) : MainEvent
}
