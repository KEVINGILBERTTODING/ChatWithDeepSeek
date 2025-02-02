package com.example.deepseek.domain.repositories
import com.example.deepseek.data.remote.RequestMessageModel
import com.example.deepseek.data.remote.ResponseMessageModel
import retrofit2.http.Body

interface ChatRepository {
    suspend fun sendCommand(@Body request: RequestMessageModel) : Result<ResponseMessageModel?>
}