package com.example.ourfood.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ourfood.data.api.ProfileRepository

class ViewModelHomeFactory(private val repository: ProfileRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelHome::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ViewModelHome(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
