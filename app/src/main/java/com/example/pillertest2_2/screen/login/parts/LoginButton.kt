package com.example.pillertest2_2.screen.login.parts

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pillertest2_2.screen.login.logic.LoginState
import com.example.pillertest2_2.screen.login.logic.LoginViewModel

@Composable
fun LoginButton (
){
    val loginViewModel = hiltViewModel<LoginViewModel>()
    val uiState by loginViewModel.loginState.collectAsStateWithLifecycle()

    Button(
        modifier = Modifier
            .clip(RoundedCornerShape(24.dp))
            .size(width = 100.dp, height = 50.dp),
        onClick = {loginViewModel.login()},
        colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
        enabled = uiState !is LoginState.Loading // Letiltás betöltés alatt
    ){
        Text("Login")
    }
}
