package com.example.ourfood.data.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserProfileDto(
    @SerialName("user_id")
    val userId: String,

    val users: UserDataDto
)

@Serializable
data class UserDataDto(
    val uid: String,
    val name: String
)