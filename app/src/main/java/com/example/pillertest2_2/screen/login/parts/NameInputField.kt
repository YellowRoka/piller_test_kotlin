package com.example.pillertest2_2.screen.login.parts


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pillertest2_2.screen.login.logic.LoginViewModel

@Composable
fun NameInputField(){
    val loginViewModel = hiltViewModel<LoginViewModel>()
    //val uiState by loginViewModel.loginState.collectAsStateWithLifecycle()

    OutlinedTextField(
        value = loginViewModel.username,
        onValueChange = { value -> loginViewModel.changedUserName(value)},
        label = { Text("Name") },
        singleLine = true,
        isError = loginViewModel.usernameError != null,
        supportingText = {
            loginViewModel.usernameError?.let{
                Text(it)
            }
         },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "user_icon",
                tint = Color.Gray,
            )
        },
    )
}
