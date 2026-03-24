package com.example.ourfood.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ourfood.R
import com.example.ourfood.ui.theme.PrimaryBlue
import com.example.ourfood.ui.theme.WhitePure

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewCadastro() {
    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var celular by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }



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
            text = ("Registra uma nova Conta"),
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineLarge,
            color = PrimaryBlue,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // Campo Nome
        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome Completo") },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.small
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Campo Email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("E-mail") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.small
        )

        Spacer(modifier = Modifier.height(12.dp))

        // NOVO: Campo Celular
        OutlinedTextField(
            value = celular,
            onValueChange = {
                // Filtro simples para aceitar apenas números
                if (it.all { char -> char.isDigit() }) celular = it
            },
            label = { Text("Celular (DDD + Número)") },
            placeholder = { Text("11999999999") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.small
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Campo Senha
        OutlinedTextField(
            value = senha,
            onValueChange = { senha = it },
            label = { Text("Senha") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.small
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Botão Cadastrar
        Button(
            onClick = { mostrarAlerta = true },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
            shape = MaterialTheme.shapes.small
        ) {
            Text("Finalizar Cadastro", fontSize = 18.sp)
        }
    }
}

// O Preview deve ficar FORA da função ViewCadastro
@Preview(showBackground = true)
@Composable
fun CadastroPreview() {
    ViewCadastro()
}