package com.example.pillertest2_2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pillertest2_2.screen.login.LoginScreen
import com.example.pillertest2_2.screen.login.logic.LoginViewModel
import com.example.pillertest2_2.screen.splash.SplashScreen
import com.example.pillertest2_2.screen.splash.logic.SplashViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pillertest2_2.screen.details.DetailsView
import com.example.pillertest2_2.screen.details.logic.DetailsViewModel
import com.example.pillertest2_2.screen.error.ErrorView
import com.example.pillertest2_2.screen.main_screen.MainScreen
import com.example.pillertest2_2.screen.main_screen.logic.MainViewModel


@Composable
fun NavigationStack() {
    val navController = rememberNavController()

    NavHost(
        navController    = navController,
        startDestination = Routes.Splash.route
    ) {

        composable(route = Routes.Splash.route) {
            val splashViewModel: SplashViewModel = hiltViewModel()
            SplashScreen(navController, splashViewModel)
        }

        composable(route = Routes.Login.route) {
            val loginViewModel: LoginViewModel = hiltViewModel()
            LoginScreen(navController,loginViewModel)
        }

        composable(route = Routes.Main.route){
            val mainViewModel : MainViewModel = hiltViewModel()
            MainScreen(navController, mainViewModel)
        }

        composable(route = Routes.Details.route){
            val detailsViewModel : DetailsViewModel = hiltViewModel()
            DetailsView(navController,detailsViewModel)
        }

        composable(route = Routes.Error.route){
            ErrorView(navController)
        }
    }
}
