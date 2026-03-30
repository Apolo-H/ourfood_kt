package com.example.ourfood.data.api

import com.example.ourfood.data.KtorClient
import com.example.ourfood.data.dtos.UserProfileDto
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders

class ProfileRepository {

    private val client = KtorClient.httpClient


    suspend fun getUserProfile(token: String): Result<UserProfileDto> {
        return try {
            val response = client.get("/profiles/me") {
                header(HttpHeaders.Authorization, "Bearer $token")
            }

            if (response.status.value == 200) {
                val profile = response.body<UserProfileDto>()
                Result.success(profile)
            } else {
                // Aqui capturamos o "Unauthorized" sem quebrar o app
                Result.failure(Exception("Erro ${response.status.value}: Não autorizado"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}