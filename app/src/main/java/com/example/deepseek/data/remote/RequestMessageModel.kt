package com.example.deepseek.data.remote

import com.google.gson.JsonArray

data class RequestMessageModel (
    val model: String? = "",
    val messages: List<MessageModel>,
    val stream: Boolean = false
)