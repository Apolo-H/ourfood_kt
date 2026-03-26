package com.example.ourfood.screens.cadastro

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ourfood.data.api.RegisterRepository
import com.example.ourfood.data.dtos.RegisterDto
import kotlinx.coroutines.launch

class ViewModelCadastro(
    private val repository: RegisterRepository = RegisterRepository()
) : ViewModel() {

    var nome by mutableStateOf("")
    var email by mutableStateOf("")
    var celular by mutableStateOf("")
    var senha by mutableStateOf("")

    var confirmarsenha by mutableStateOf("")
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

        if (nome.isBlank() || email.isBlank() || celular.isBlank() || senha.isBlank()) {
            mensagemErro = "Preencha todos os campos obrigatórios."
            return
        }

        if (senha != confirmarsenha) {
            mensagemErro = "As senhas não coincidem."
            return
        }


        viewModelScope.launch {
            isLoading = true
            mensagemErro = null

            val userDto = RegisterDto(
                name = nome,
                email = email,
                telephone = celular,
                password = senha,
                role = "Donor"
            )

            val result = repository.registerUsers(userDto)

            if (result.isSuccess) {
                cadastroSucesso = true
                limpaCampos()
            } else {
                cadastroSucesso = false
                mensagemErro = result.exceptionOrNull()?.message ?: "Erro desconhecido ao cadastrar."
            }

            isLoading = false
        }
    }
}