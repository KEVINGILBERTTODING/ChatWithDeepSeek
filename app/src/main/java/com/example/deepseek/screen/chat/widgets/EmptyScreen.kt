package com.example.deepseek.screen.chat.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.deepseek.R
import com.example.deepseek.screen.main.Greeting
import com.example.deepseek.ui.theme.DeepSeekTheme

@Composable
fun EmptyScreen() {
    Column(
        modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState())
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(R.drawable.ic_explain),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.padding(top = 5.dp))
        Text(
            text = stringResource(R.string.explain),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.surface,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.padding(top = 18.dp))
        TextContainer(stringResource(R.string.explain_quantum_physics))
        Spacer(modifier = Modifier.padding(top = 8.dp))
        TextContainer(stringResource(R.string.what_are_wormholes))
        Spacer(modifier = Modifier.padding(top = 37.dp))
        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(R.drawable.ic_edit),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.padding(top = 5.dp))
        Text(
            text = stringResource(R.string.write_edit),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.surface,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.padding(top = 18.dp))
        TextContainer(stringResource(R.string.write_a_tweet_about_global_warming))
        Spacer(modifier = Modifier.padding(top = 8.dp))
        TextContainer(stringResource(R.string.write_a_poem))
        Spacer(modifier = Modifier.padding(top = 8.dp))
        TextContainer(stringResource(R.string.write_a_rap_song))

        Spacer(modifier = Modifier.padding(top = 37.dp))
        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(R.drawable.ic_translate),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.padding(top = 5.dp))
        Text(
            text = stringResource(R.string.translate),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.surface,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.padding(top = 18.dp))
        TextContainer(stringResource(R.string.how_do_you_say))
        Spacer(modifier = Modifier.padding(top = 8.dp))
        TextContainer(stringResource(R.string.write_a_poem))

    }
}

@Preview(showBackground = true)
@Composable
fun EmptyPreview() {
    DeepSeekTheme {
        EmptyScreen()
    }
}

@Composable
private fun TextContainer(content: String) {
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
                    color = MaterialTheme.colorScheme.background,
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
                text = content,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.surface,
                textAlign = TextAlign.Center
            )
        }

    }
}