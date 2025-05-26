package com.example.pillertest2_2.screen.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.pillertest2_2.navigation.Routes
import com.example.pillertest2_2.screen.login.logic.LoginEvent
import com.example.pillertest2_2.screen.login.logic.LoginState
import com.example.pillertest2_2.screen.login.logic.LoginViewModel
import com.example.pillertest2_2.screen.login.parts.LoadingProgress
import com.example.pillertest2_2.screen.login.parts.LoginButton
import com.example.pillertest2_2.screen.login.parts.NameInputField
import com.example.pillertest2_2.screen.login.parts.PasswordInputField
import kotlinx.coroutines.flow.collectLatest


@Composable
fun LoginScreen (
    navController: NavController,
    loginViewModel : LoginViewModel = hiltViewModel()
){
    val context = LocalContext.current
    val uiState by loginViewModel.loginState.collectAsStateWithLifecycle() // UI állapot gyűjtése

    LaunchedEffect(key1 = Unit) {
        loginViewModel.events.collectLatest {
            event -> when (event) {
                is LoginEvent.LoginSuccess -> {
                    val message = event.message
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    navController.navigate(Routes.Main.route) {
                        popUpTo(Routes.Login.route) {
                            inclusive = true
                        }
                    }
                }
                is LoginEvent.GeneralError ->{
                    val message = event.message
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    Box(
        contentAlignment = Alignment.Center,
    ){
        ElevatedCard(
            elevation = CardDefaults.cardElevation( defaultElevation = 6.dp ),
            modifier  = Modifier
                .height(300.dp)
                .padding(20.dp)
        ){
            Column (
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                NameInputField()
                Spacer(Modifier.height(10.dp))
                PasswordInputField()
                Spacer(modifier = Modifier.height(10.dp))

                if(uiState is LoginState.Loading){
                    LoadingProgress()
                }
                else{
                    LoginButton()
                }
            }
        }
    }
}
