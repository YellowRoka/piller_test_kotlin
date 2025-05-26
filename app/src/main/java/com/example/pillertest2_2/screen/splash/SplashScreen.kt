package com.example.pillertest2_2.screen.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pillertest2_2.navigation.Routes
import com.example.pillertest2_2.screen.splash.logic.SplashUIState
import com.example.pillertest2_2.screen.splash.logic.SplashViewModel
import com.example.pillertest2_2.screen.splash.parts.UniqueVideoView


@Composable
fun SplashScreen(
    navController: NavController,
    splashViewModel: SplashViewModel = hiltViewModel()
){

    splashViewModel.getVideoLink()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center

    ){
        when (splashViewModel.splashUIState) {
            is SplashUIState.Init -> {
                CircularProgressIndicator()
            }
            is SplashUIState.VideoLink -> UniqueVideoView(
                link     = (splashViewModel.splashUIState as SplashUIState.VideoLink).link,
                callBack = {
                    navController.navigate(Routes.Login.route) {
                        popUpTo(Routes.Splash.route) { inclusive = true } // ne tudjon visszamenni
                    }
                }
            )
        }
    }
}
