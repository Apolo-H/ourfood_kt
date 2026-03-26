package com.example.ourfood.screens.cadastro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ourfood.R
import com.example.ourfood.ui.theme.PrimaryBlue
import com.example.ourfood.ui.theme.WhitePure
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ourfood.screens.login.ViewLogin

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun ViewCadastro(viewModel: ViewModelCadastro = viewModel(), navController: NavController? = null) {

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel.cadastroSucesso) {
        if (viewModel.cadastroSucesso) {
            snackbarHostState.showSnackbar("Oba! Sua conta no OurFood foi criada.")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = WhitePure)
            .verticalScroll(rememberScrollState())
            .padding(top = 60.dp, start = 24.dp, end = 24.dp, bottom = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo OurFood",
            modifier = Modifier.size(80.dp),
            contentScale = ContentScale.Fit
        )

        Text(
            text = "Registra uma nova Conta",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryBlue,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // Campo Nome
        OutlinedTextField(
            value = viewModel.nome,
            onValueChange = { viewModel.nome = it },
            label = { Text("Nome Completo") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = LocalTextStyle.current.copy(
                fontSize = 16.sp,
                lineHeight = 20.sp
            ),
            shape = MaterialTheme.shapes.medium,
            singleLine = true,
            enabled = !viewModel.isLoading
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Campo Email
        OutlinedTextField(
            value = viewModel.email,
            onValueChange = { viewModel.email = it },
            label = { Text("E-mail") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth(),
            textStyle = LocalTextStyle.current.copy(
                fontSize = 16.sp,
                lineHeight = 20.sp
            ),
            shape = MaterialTheme.shapes.medium,
            singleLine = true,
            enabled = !viewModel.isLoading
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Campo Celular
        OutlinedTextField(
            value = viewModel.celular,
            onValueChange = { if (it.all { char -> char.isDigit() }) viewModel.celular = it },
            label = { Text("Celular (DDD + Número)") },
            placeholder = { Text("11999999999") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            textStyle = LocalTextStyle.current.copy(
                fontSize = 16.sp,
                lineHeight = 20.sp
            ),
            shape = MaterialTheme.shapes.medium,
            singleLine = true,
            enabled = !viewModel.isLoading
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Campo Senha
        OutlinedTextField(
            value = viewModel.senha,
            onValueChange = { viewModel.senha = it },
            label = { Text("Senha") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(),
            textStyle = LocalTextStyle.current.copy(
                fontSize = 16.sp,
                lineHeight = 20.sp
            ),
            shape = MaterialTheme.shapes.medium,
            singleLine = true,
            enabled = !viewModel.isLoading
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = viewModel.confirmarsenha,
            onValueChange = { viewModel.confirmarsenha = it },
            label = { Text("Confirmar Senha") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(),
            textStyle = LocalTextStyle.current.copy(
                fontSize = 16.sp,
                lineHeight = 20.sp
            ),
            shape = MaterialTheme.shapes.medium,
            singleLine = true,
            enabled = !viewModel.isLoading
        )

        viewModel.mensagemErro?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Botão Finalizar
        Button(
            onClick = { viewModel.finalizarCadastro() },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            enabled = !viewModel.isLoading,
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
            shape = MaterialTheme.shapes.medium
        )
        {
            if (viewModel.isLoading) {
                CircularProgressIndicator(
                    color = WhitePure, modifier = Modifier.size(24.dp), strokeWidth = 2.dp
                )
            } else {
                Text("Finalizar Cadastro", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            }
        }

        Text(
            text = "login",
            color = PrimaryBlue,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(8.dp)
                .clickable {

                    navController?.navigate("login") {
                        popUpTo("cadastro") { inclusive = true }
                    }
                }
        )

        SnackbarHost(hostState = snackbarHostState)
    }
}


@Preview
@Composable
fun PreviewTela() {
    ViewCadastro()
}
