package com.example.deepseek.screen.chat

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.deepseek.base.constant.Constant
import com.example.deepseek.data.remote.MessageModel
import com.example.deepseek.data.remote.RequestMessageModel
import com.example.deepseek.domain.models.SendChatModel
import com.example.deepseek.domain.repositories.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    val chatRepository: ChatRepository,
    application: Application
) : AndroidViewModel(application) {
    private val _chatList = MutableStateFlow(mutableStateListOf<MessageModel>())
    val chatList : StateFlow<List<MessageModel>> = _chatList
    private val _sendMessageState = MutableStateFlow<SendChatModel>(SendChatModel.Idle)
    val sendMessageState : StateFlow<SendChatModel> = _sendMessageState
    val TAG = "chat viewmodel"

    fun sendMessage(message: String) {
        viewModelScope.launch {
            _chatList.value.add(MessageModel(Constant.USER_ROLE_KEY, message))
            _sendMessageState.value = SendChatModel.Loading
            try {
                val messageList = listOf(
                    MessageModel(Constant.USER_ROLE_KEY, message),
                )
                val requestMessageModel = RequestMessageModel(
                    Constant.MODEL_NAME,
                    messageList,
                    false
                )
                val response = chatRepository.sendCommand(requestMessageModel).getOrThrow()
                _sendMessageState.value = SendChatModel.Success
                response?.message?.let {
                    val cleanAnswer = removeThinkTag(response.message.content ?: "")
                    val messageClean = MessageModel(Constant.ASSISTANT_ROLE_KEY, cleanAnswer)
                    _chatList.value.add(messageClean)
                }
            }catch (e: Exception) {
                Log.d(TAG, "sendMessage: ${e.message}")
                _sendMessageState.value = SendChatModel.Error(e.message)
            }
        }
    }
    fun clearAllChat() {
        _chatList.value.clear()
    }
    fun removeThinkTag(input: String): String {
        val result = input.replace(Regex("<think>.*?</think>", RegexOption.DOT_MATCHES_ALL), "")
        return result.replace(Regex("(\n|\r|\\s)+"), " ").trim()
    }
}

