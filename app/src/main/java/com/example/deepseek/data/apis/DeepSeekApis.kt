package com.example.deepseek.data.apis

import com.example.deepseek.data.remote.RequestMessageModel
import com.example.deepseek.data.remote.ResponseMessageModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface DeepSeekApis {
    @POST("chat")
    suspend fun sendCommand(
        @Body request: RequestMessageModel
    ) : Response<ResponseMessageModel>
}