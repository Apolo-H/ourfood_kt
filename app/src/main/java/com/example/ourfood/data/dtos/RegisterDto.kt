package com.example.ourfood.data.dtos

import kotlinx.serialization.Serializable


@Serializable
data class  RegisterDto (
    val name: String,
    val email: String,
    val password: String,
    val role: String,
    val telephone: String
)