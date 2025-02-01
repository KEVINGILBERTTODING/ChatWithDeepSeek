package com.example.deepseek.domain.repositoryimpl

import com.example.deepseek.data.apis.DeepSeekApis
import com.example.deepseek.data.remote.MessageModel
import com.example.deepseek.data.remote.ResponseMessageModel
import com.example.deepseek.domain.repositories.ChatRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    val apis: DeepSeekApis
) : ChatRepository {
    override suspend fun sendCommand(message: MessageModel): Result<ResponseMessageModel?> {
        return try {
            val response = apis.sendCommand(message).getOrThrow()
            Result.success(response)
        }catch (e: Exception) {
            Result.failure(e)
        }
    }
}