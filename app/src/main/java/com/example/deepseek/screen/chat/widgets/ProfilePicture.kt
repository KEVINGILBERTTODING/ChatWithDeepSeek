package com.example.deepseek.screen.chat.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.deepseek.R
import com.example.deepseek.ui.theme.DeepSeekTheme

@Composable
fun ProfilePicture() {
    Box() {
        Box(modifier = Modifier
            .padding(top = 1.dp)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = CircleShape
            )
            .size(40.dp)
        )
        Box(modifier = Modifier
            .padding(start = 2.dp)
            .size(38.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.surface,
                shape = CircleShape
            )
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = CircleShape
            )) {
            Image(
                modifier = Modifier
                    .height(28.dp)
                    .width(28.dp)
                    .align(Alignment.Center),
                painter = painterResource(R.drawable.main_logo),
                contentDescription = ""
            )
        }
    }
}
//
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DeepSeekTheme {
        ProfilePicture()
    }
}