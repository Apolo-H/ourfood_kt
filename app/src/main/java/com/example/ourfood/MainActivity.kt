package com.example.ourfood

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ourfood.screens.cadastro.ViewCadastro
import com.example.ourfood.screens.home.ViewHome
import com.example.ourfood.screens.login.ViewLogin

@Composable
fun SetupNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            // O navController deve ser passado AQUI, fora das chaves do onLoginSuccess
            ViewLogin(
                navController = navController,
                onLoginSuccess = { token ->
                    navController.navigate("home/$token") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }
        composable(
            "home/{token}",
            arguments = listOf(navArgument("token") {
                type = NavType.StringType })
        ) { backStackEntry ->
            val token = backStackEntry.arguments?.getString("token") ?: ""
            ViewHome(token = token)
        }
        composable("cadastro") {
            ViewCadastro(
                navController = navController,
            )
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SetupNavGraph()
        }
    }
}
