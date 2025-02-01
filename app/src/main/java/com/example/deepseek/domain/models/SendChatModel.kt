package com.example.deepseek.domain.models

import com.example.deepseek.data.remote.MessageModel

sealed class SendChatModel {
    data object Loading : SendChatModel()
    data class Succcess(val message : MessageModel?) : SendChatModel()
    data class Error(val message: String?) : SendChatModel()
    data object Idle : SendChatModel()
}