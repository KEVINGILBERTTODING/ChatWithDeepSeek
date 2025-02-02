package com.example.deepseek.domain.repositoryimpl

import com.example.deepseek.data.apis.DeepSeekApis
import com.example.deepseek.data.remote.RequestMessageModel
import com.example.deepseek.data.remote.ResponseMessageModel
import com.example.deepseek.domain.repositories.ChatRepository
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    val apis: DeepSeekApis
) : ChatRepository {
    override suspend fun sendCommand(request: RequestMessageModel): Result<ResponseMessageModel?> {
        return try {
            val responseResult = apis.sendCommand(request)
            if (responseResult.isSuccessful) {
                val responseBody = responseResult.body()
                Result.success(responseBody)
            }else {
                Result.failure(Exception(responseResult.message()))
            }
        }catch (e: Exception) {
            Result.failure(e)
        }
    }

}