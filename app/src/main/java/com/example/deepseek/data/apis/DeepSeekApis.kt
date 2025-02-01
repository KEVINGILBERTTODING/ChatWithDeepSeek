package com.example.deepseek.data.apis

import com.example.deepseek.data.remote.MessageModel
import com.example.deepseek.data.remote.ResponseMessageModel
import retrofit2.http.Body
import retrofit2.http.POST

interface DeepSeekApis {
    @POST
    suspend fun sendCommand(
        @Body request: MessageModel
    ) : Result<ResponseMessageModel>
}