package com.example.ourfood.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ourfood.ui.theme.WhitePure

@Composable
fun ViewHome() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = WhitePure)
            .verticalScroll(rememberScrollState())
            .padding(top = 60.dp, start = 24.dp, end = 24.dp, bottom = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "TELA DE HOME"
        )
    }
}