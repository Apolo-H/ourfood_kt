package com.example.ourfood.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ourfood.data.api.UserRepository
import com.example.ourfood.data.dtos.Users
import kotlinx.coroutines.launch

class CadastroViewModel(
    private val repository: UserRepository = UserRepository()
) : ViewModel() {

    var nome by mutableStateOf("")
    var email by mutableStateOf("")
    var celular by mutableStateOf("")
    var senha by mutableStateOf("")
    var isLoading by mutableStateOf(false)
    var mensagemErro by mutableStateOf<String?>(null)

    var cadastroSucesso by mutableStateOf(false)

    private fun limpaCampos() {
        nome = ""
        email = ""
        celular = ""
        senha = ""
    }

    fun finalizarCadastro() {
        // Validação local rápida
        if (nome.isBlank() || email.isBlank() || celular.isBlank() || senha.isBlank()) {
            mensagemErro = "Preencha todos os campos obrigatórios."
            return
        }

        viewModelScope.launch {
            isLoading = true
            mensagemErro = null

            val userDto = Users(
                name = nome, email = email, telephone = celular, password = senha, role = "Donor"
            )

            val result = repository.registerUsers(userDto)

            if (result.isSuccess) {
                cadastroSucesso = true
                limpaCampos()
            } else {
                cadastroSucesso = false
                mensagemErro =
                    result.exceptionOrNull()?.message ?: "Erro desconhecido ao cadastrar."
            }

            isLoading = false
        }
    }
}