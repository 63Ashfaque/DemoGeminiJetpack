package com.ashfaque.demogeminijetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ashfaque.demogeminijetpack.GlobalState.chatList
import com.ashfaque.demogeminijetpack.ui.compose.ChatScreen
import com.ashfaque.demogeminijetpack.ui.compose.MainScreenBox
import com.ashfaque.demogeminijetpack.ui.theme.DarkGray
import com.ashfaque.demogeminijetpack.ui.theme.DemoGeminiJetpackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemoGeminiJetpackTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = DarkGray
                ) {
                    MyApp()
                }
            }
        }
    }

}

@Composable
fun MyApp() {
    // BoxWithBackground()
    MainScreenBox()
}


@Composable
fun BoxWithBackground() {
    Box() {
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.End
        ) {
            ChatScreen(chatList)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DemoGeminiJetpackTheme {
        MyApp()
    }
}

