package com.example.pillertest2_2.screen.splash.logic

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.pillertest2_2.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel(){

    var splashUIState: SplashUIState by mutableStateOf(SplashUIState.Init)

    fun getVideoLink(){
        splashUIState = SplashUIState.VideoLink(R.raw.piller_logo_animated)
    }
}
