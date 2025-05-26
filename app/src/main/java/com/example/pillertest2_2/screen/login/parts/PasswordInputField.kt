package com.example.pillertest2_2.screen.login.parts

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pillertest2_2.screen.login.logic.LoginViewModel

@Composable
fun PasswordInputField (){
    val loginViewModel = hiltViewModel<LoginViewModel>()

    OutlinedTextField(
        value = loginViewModel.password,
        onValueChange = { value -> loginViewModel.changedPassword(value)},
        label = { Text("Password") },
        singleLine = true,
        isError = loginViewModel.passwordError != null,
        supportingText = {
            loginViewModel.passwordError?.let{
                Text(it)
            }
        },
        visualTransformation =
            if(loginViewModel.isPasswordVisible)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Lock,
                contentDescription = "password_icon",
                tint = Color.Gray
            )
        },
        trailingIcon = {
            IconButton(
                onClick = {loginViewModel.changePasswordVisibility()}
            ) {
                Icon(
                    imageVector =
                        if(loginViewModel.isPasswordVisible)
                            Icons.Filled.Info
                        else
                            Icons.Outlined.Info,
                    contentDescription = "password_visibility_icon",
                    tint = Color.Black
                )
            }
        }
    )
}
