package com.example.ourfood

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ourfood.data.api.LoginRepository
import com.example.ourfood.data.local.TokenManager
import com.example.ourfood.screens.login.ViewModelLogin

class ViewModelLoginFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelLogin::class.java)) {
            return ViewModelLogin(
                repository = LoginRepository(),
                tokenManager = TokenManager(context)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
