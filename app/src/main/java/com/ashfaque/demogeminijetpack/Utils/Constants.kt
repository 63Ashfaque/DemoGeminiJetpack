package com.ashfaque.demogeminijetpack.Utils

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.platform.LocalContext
import com.ashfaque.demogeminijetpack.model.ChatModel

// Access your API key as a Build Configuration variable
val apiKey1 = "AI"
val apiKey2 = "zaSyCzgnHd3PNE1uBanFMrtpOY8ACDzUQ9x"
val apiKey3 = "_g"

val dateFormate="hh:mm a"

const val tableName="chatTable"

object GlobalState {
    val chatList = mutableStateListOf(
        ChatModel(1,"fd1",
            "Hi! How can I help you today?",
            TypeClass.AI,
            Utils().getCurrentDateTime(dateFormate)
        ),
    )
}