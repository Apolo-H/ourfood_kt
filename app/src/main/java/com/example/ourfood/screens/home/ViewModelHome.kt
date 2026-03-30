package com.example.ourfood.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ourfood.data.api.ProfileRepository
import com.example.ourfood.data.dtos.UserProfileDto
import kotlinx.coroutines.launch

class ViewModelHome(private val repository: ProfileRepository) : ViewModel() {

    private val _userProfile = mutableStateOf<UserProfileDto?>(null)
    val userProfile: State<UserProfileDto?> = _userProfile

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    fun loadUserProfile(token: String) {
        viewModelScope.launch {
            repository.getUserProfile(token)
                .onSuccess {
                    _userProfile.value = it
                }
                .onFailure {
                    _error.value = it.message
                }
        }
    }
}
