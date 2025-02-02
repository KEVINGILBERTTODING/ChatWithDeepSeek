package com.example.deepseek.screen.chat.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.deepseek.data.remote.MessageModel
import com.example.deepseek.domain.models.SendChatModel
import com.example.deepseek.screen.main.Greeting
import com.example.deepseek.ui.theme.DeepSeekTheme

@Composable
fun BubleChat(data: MessageModel) {
    val isUser = data.role.equals("user")
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement =
    if (isUser) Arrangement.Absolute.Right else Arrangement.Absolute.Left) {
        if (isUser.not()) {
            ProfilePicture()
            Spacer(modifier = Modifier.padding(end = 5.dp))
        }
        Box(modifier = if (isUser) Modifier.padding(start = 50.dp)
        else  Modifier.padding(end = 20.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(16.dp)
                        )
            ) {
                Box(
                    modifier = Modifier
                        .padding(start = 5.dp, bottom = 5.dp)
                        .background(
                            color = if (isUser)
                                MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .border(
                            width = 2.dp,
                            color = MaterialTheme.colorScheme.surface,
                            RoundedCornerShape(16.dp)
                        )
                ) {
                    Text(
                        modifier = Modifier
                            .padding(
                                top = 10.dp,
                                bottom = 10.dp,
                                start = 18.dp,
                                end = 18.dp),
                        text = data.content ?: "",
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 14.sp,
                        color = if(isUser)
                                MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.surface,
                        textAlign = if (data.role.equals("user")) TextAlign.End else TextAlign.Left
                    )
                }

            }

        }


    }
}
