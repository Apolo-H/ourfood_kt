package com.example.ourfood.screens.login


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ourfood.R
import com.example.ourfood.ui.theme.LightGray
import com.example.ourfood.ui.theme.PrimaryBlue
import com.example.ourfood.ui.theme.WhitePure

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewLogin(viewModel: ViewModelLogin = viewModel(), onLoginSuccess: () -> Unit = {}, navController: NavController? = null) {

    LaunchedEffect(viewModel.loginSucesso) {
        if (viewModel.loginSucesso) {
            onLoginSuccess()
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
        Image(
            painter = painterResource(id = R.drawable.logo_ourfood),
            contentDescription = "Logo OurFood",
            modifier = Modifier.size(250.dp),
            contentScale = ContentScale.Fit
        )

        OutlinedTextField(
            value = viewModel.email,
            onValueChange = { viewModel.email = it },
            label = { Text("Email:") },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.small,
            textStyle = LocalTextStyle.current.copy(
                fontSize = 16.sp,
                lineHeight = 20.sp
            ),
            singleLine = true,
            enabled = !viewModel.isLoading
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = viewModel.password,
            onValueChange = { viewModel.password = it },
            label = { Text("Senha:") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            textStyle = LocalTextStyle.current.copy(
                fontSize = 16.sp,
                lineHeight = 20.sp
            ),
            shape = MaterialTheme.shapes.small,
            singleLine = true,
            enabled = !viewModel.isLoading
        )

        Text(
            text = "Esqueceu a senha?",
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = LightGray,
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)

        )

        viewModel.mensagemErro?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }


        Spacer(modifier = Modifier.height(32.dp))


        Button(
            onClick = { viewModel.finalizarLogin() },
            modifier = Modifier
                .width(250.dp)
                .height(60.dp),
            enabled = !viewModel.isLoading,
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
            shape = MaterialTheme.shapes.medium
        ) {

            if (viewModel.isLoading) {
                CircularProgressIndicator(
                    color = WhitePure, modifier = Modifier.size(24.dp), strokeWidth = 2.dp
                )
            } else {
                Text("Entrar", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            }

        }

        Text(
            text = "Cadastro",
            color = PrimaryBlue,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(8.dp)
                .clickable {

                    navController?.navigate("cadastro") {
                        popUpTo("login") { inclusive = true }
                    }
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTela() {
    ViewLogin()
}
