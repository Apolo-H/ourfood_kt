package com.example.ourfood.data.dtos

import kotlinx.serialization.Serializable


@Serializable
data class  Users (
    val name: String,
    val email: String,
    val password: String,
    val role: String,
    val telephone: String
)
