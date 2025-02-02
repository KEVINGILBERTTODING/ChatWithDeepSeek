package com.example.deepseek.domain.models

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.deepseek.data.remote.MessageModel

sealed class SendChatModel {
    data object Loading : SendChatModel()
    data object Success: SendChatModel()
    data class Error(val message: String?) : SendChatModel()
    data object Idle : SendChatModel()
}