package com.example.deepseek.screen.chat

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.deepseek.R
import com.example.deepseek.data.remote.MessageModel
import com.example.deepseek.domain.models.SendChatModel
import com.example.deepseek.screen.chat.widgets.BubleChat
import com.example.deepseek.screen.chat.widgets.EmptyScreen
import com.example.deepseek.screen.chat.widgets.LoadingMessage
import com.example.deepseek.screen.chat.widgets.ProfilePicture
import com.example.deepseek.ui.theme.DeepSeekTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DeepSeekTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(innerPadding)
                }
            }
        }
    }
}

@Composable
private fun MainScreen(paddingValues: PaddingValues) {
    val keywordVal = remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val chatViewModel : ChatViewModel = viewModel()
    val chatList by chatViewModel.chatList.collectAsState()
    val stateSendMessage by chatViewModel.sendMessageState.collectAsState()
    val context = LocalContext.current

    Box(modifier = Modifier
        .fillMaxSize()
        .imePadding()
        .padding(start = 20.dp, end = 20.dp)) {
        Column(
            modifier = Modifier.safeContentPadding(),
        ) {
            AppBar(chatViewModel)
            Spacer(modifier = Modifier.padding(top = 10.dp))
            if (chatList.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .weight(1f)
                ) {
                    EmptyScreen()
                }
            }else {
                LazyColumn(
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                ) {
                    items(chatList) { chat ->
                        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp)) {
                            BubleChat(chat)
                        }
                    }
                }
            }
            when(val sendState = stateSendMessage) {
                is SendChatModel.Loading -> {
                    LoadingMessage()
                }
                is SendChatModel.Error -> {
                    Toast.makeText(context, sendState.message, Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }
            Box(
                Modifier
                    .padding(bottom = 20.dp, top = 10.dp)
                    .border(
                        width = 2.dp,
                        shape = RoundedCornerShape(20.dp),
                        color = MaterialTheme.colorScheme.surface
                    )
            ){
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 5,
                    value = keywordVal.value,
                    placeholder =  {
                        Text(
                            text = stringResource(R.string.write_ur_message),
                            color = MaterialTheme.colorScheme.surface,
                            style = MaterialTheme.typography.bodyMedium,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Left
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    ),
                    onValueChange = {
                        keywordVal.value = it
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Search,
                        keyboardType = KeyboardType.Text
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            keyboardController?.hide()
                            focusManager.clearFocus()
                            when(stateSendMessage) {
                                is SendChatModel.Loading -> {}
                                else -> {
                                    if (keywordVal.value.isNotEmpty()) {
                                        chatViewModel.sendMessage(keywordVal.value)
                                        keywordVal.value = ""
                                    }
                                }
                            }
                        }
                    )
                )
            }
        }

    }
}

@Composable
private fun AppBar(chatViewModel: ChatViewModel) {
    val chatList by chatViewModel.chatList.collectAsState()
    val chatState by chatViewModel.sendMessageState.collectAsState()
    Row(modifier = Modifier.fillMaxWidth()) {
        ProfilePicture()
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.align(Alignment.CenterVertically)) {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = stringResource(R.string.online),
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
        Spacer(Modifier.weight(1f))
        if (chatList.isEmpty().not()) {
            when(chatState) {
                is SendChatModel.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp).align(Alignment.CenterVertically)
                    )
                }
                else -> {
                    Box(
                        modifier = Modifier.wrapContentSize()
                    ) {
                        IconButton(
                            onClick = {
                                chatViewModel.clearAllChat()
                            }
                        ) {
                            Image(
                                modifier = Modifier.size(28.dp),
                                painter = painterResource(R.drawable.ic_trash),
                                contentDescription = ""
                            )
                        }
                    }
                }
            }
        }

    }
}
