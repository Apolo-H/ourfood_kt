package com.example.ourfood.data.dtos

import kotlinx.serialization.Serializable

@Serializable
data class LoginDto (
    val email: String,
    val password: String

)
