package com.example.pillertest2_2.screen.login.logic

//Ha hiba olyan esemény történt amit csak 1x kell megjeleníteni pl.: hálózati hiba, navigálás, db hiba, toast...
sealed interface LoginEvent {
    data class LoginSuccess(val message : String?) : LoginEvent
    data class GeneralError(val message : String?) : LoginEvent
}
