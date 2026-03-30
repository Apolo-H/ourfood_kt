package com.example.ourfood.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ourfood.data.api.LoginRepository
import com.example.ourfood.data.dtos.LoginDto
import kotlinx.coroutines.launch

class ViewModelLogin(
    private val repository: LoginRepository = LoginRepository(),
            private val tokenManager: com.example.ourfood.data.local.TokenManager
) : ViewModel() {

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var isLoading by mutableStateOf(false)
    var mensagemErro by mutableStateOf<String?>(null)
    var loginSucesso by mutableStateOf(false)
    var token by mutableStateOf<String?>(null)


    fun finalizarLogin(onSuccess: (String) -> Unit) {


        if (email.isBlank() || password.isBlank()) {
            mensagemErro = "Preencha todos os campos obrigatórios."
            return
        }

        viewModelScope.launch {
            isLoading = true
            mensagemErro = null


            val loginDto = LoginDto(
                email = email,
                password = password
            )

            val result = repository.loginRepository(loginDto)

            if (result.isSuccess) {
                val receivedToken = result.getOrNull() ?: ""
                tokenManager.saveToken(receivedToken)
                token = receivedToken
                loginSucesso = true
                mensagemErro = "Login realizado com sucesso!"
                onSuccess(receivedToken)
            } else {
                loginSucesso = false

                val erroOriginal = result.exceptionOrNull()?.message
                mensagemErro = if (erroOriginal?.contains("401") == true) {
                    "E-mail ou senha inválidos. Tente novamente."
                } else {
                    "Não foi possível conectar ao servidor. Verifique sua internet."
                }
            }

            isLoading = false
        }

    }
}
