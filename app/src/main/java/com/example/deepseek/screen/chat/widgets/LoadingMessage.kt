package com.example.deepseek.screen.chat.widgets
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.deepseek.R
import com.example.deepseek.ui.theme.DeepSeekTheme

@Composable
fun LoadingMessage() {
    Row(
        modifier = Modifier.fillMaxWidth()) {
        ProfilePicture()
        Spacer(modifier = Modifier.padding(end = 5.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = stringResource(R.string.thinking),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.surface,
            fontSize = 14.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingPreview() {
    DeepSeekTheme {
        LoadingMessage()
    }
}