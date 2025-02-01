package com.example.deepseek.screen.chat

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.deepseek.domain.repositories.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    chatRepository: ChatRepository,
    application: Application
) : AndroidViewModel(application) {

}