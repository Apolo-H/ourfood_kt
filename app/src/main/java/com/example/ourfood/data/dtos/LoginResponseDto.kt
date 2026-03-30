package com.example.ourfood.data.dtos

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
    val token: String
)