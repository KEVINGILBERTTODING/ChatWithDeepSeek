package com.example.deepseek.domain.repositories

import com.example.deepseek.data.remote.MessageModel
import com.example.deepseek.data.remote.ResponseMessageModel

interface ChatRepository {
    suspend fun sendCommand(message: MessageModel) : Result<ResponseMessageModel?>
}