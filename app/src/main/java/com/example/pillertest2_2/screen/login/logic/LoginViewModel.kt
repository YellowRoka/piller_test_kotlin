package com.example.pillertest2_2.screen.login.logic

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pillertest2_2.data.repository.UserRepository
import com.example.pillertest2_2.domain.LoginFormValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val formValidator: LoginFormValidator,
    private val userRepository: UserRepository,
) : ViewModel() {

    // A by property delegate használatával automatikusan figyelhetővé válnak
    var username by mutableStateOf("")
        private set // Csak a ViewModel módosíthatja kívülről

    var password by mutableStateOf("")
        private set

    var isPasswordVisible by mutableStateOf(false)
        private set

    var usernameError by mutableStateOf<String?>(null)
        private set

    var passwordError by mutableStateOf<String?>(null)
        private set

    var loginState = MutableStateFlow<LoginState>(LoginState.Init)

    // Egyszeri események (navigáció, Toast üzenetek)
    private val _events = Channel<LoginEvent>()
    val events = _events.receiveAsFlow() // Flow-ként exponáljuk kifelé

    init {
        viewModelScope.launch {
            username = userRepository.userNameFlow.first()
            password = userRepository.passwordFlow.first()
        }
    }

    fun changedUserName(name: String) {
        username = name
        usernameError = null
    }

    fun changedPassword(pwd: String) {
        password = pwd
        passwordError = null
    }

    fun changePasswordVisibility() {
        isPasswordVisible = !isPasswordVisible
    }

    fun login() {
        //UI-ra kiható futtatás
        viewModelScope.launch {
            usernameError = formValidator.checkUserName(username)
            passwordError = formValidator.checkPassword(password)

            if(usernameError != null || passwordError != null){
                return@launch
            }

            try {
                loginState.emit(LoginState.Loading)
                userRepository.login(username, password)
                loginState.emit(LoginState.Success)
                _events.send(LoginEvent.LoginSuccess("Login successful!"))
            }
            catch (e: Exception) {
                loginState.emit(LoginState.Error(e.message.toString()))
                _events.send(LoginEvent.GeneralError(e.message.toString()))
            }
        }
    }
}
