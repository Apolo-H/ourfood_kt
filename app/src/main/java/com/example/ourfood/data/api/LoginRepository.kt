package com.example.ourfood.data.api

import com.example.ourfood.data.KtorClient
import com.example.ourfood.data.dtos.LoginDto
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.isSuccess

class LoginRepository {
    private val client = KtorClient.httpClient
    suspend fun loginRepository(loginDto: LoginDto): Result<Unit> {
        return try {
            val response = client.post("/auth/login") {
                setBody(loginDto)
            }
            if (response.status.isSuccess()) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Erro no servidor: ${response.status.value}"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}