package com.example.pillertest2_2.navigation

sealed class Routes (val route: String){
    data object Splash  : Routes("splash")
    data object Login   : Routes("login")
    data object Main    : Routes("main")
    data object Details : Routes("details")
    data object Error   : Routes("error")
}
