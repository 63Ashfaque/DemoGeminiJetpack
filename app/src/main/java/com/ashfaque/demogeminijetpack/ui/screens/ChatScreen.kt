package com.ashfaque.demogeminijetpack.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.ashfaque.demogeminijetpack.Utils.GlobalState
import com.ashfaque.demogeminijetpack.Utils.TypeClass
import com.ashfaque.demogeminijetpack.Utils.Utils
import com.ashfaque.demogeminijetpack.Utils.dateFormate
import com.ashfaque.demogeminijetpack.model.ChatModel
import com.ashfaque.demogeminijetpack.roomdb.DataBaseName
import com.ashfaque.demogeminijetpack.ui.compose.AiCardView
import com.ashfaque.demogeminijetpack.ui.compose.UserCardView
import com.ashfaque.demogeminijetpack.ui.compose.showToast
import com.ashfaque.demogeminijetpack.ui.theme.DarkGray
import com.ashfaque.demogeminijetpack.ui.theme.LightGray
import com.ashfaque.demogeminijetpack.ui.theme.White70
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ChatScreen(items: List<ChatModel>, roomId: String) {

    val context = LocalContext.current
    val dataBase = DataBaseName.getDataBase(context)

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    coroutineScope.launch {
        if(items.isNotEmpty()) {
            listState.animateScrollToItem(items.size - 1)
        }
    }
    Column {
        LazyColumn(modifier = Modifier.weight(1f), state = listState) {
            items(items) { item ->
                if (item.type == TypeClass.AI) AiCardView(item) else UserCardView(item)
            }
        }
        var promptText by remember { mutableStateOf(TextFieldValue("")) }

        Row(
            modifier = Modifier
                .height(60.dp)
                .padding(top = 8.dp),
        ) {
            ElevatedCard(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.weight(0.7f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {

                    TextField(
                        value = promptText,
                        onValueChange = { promptText = it },
                        placeholder = {
                            Text(
                                text = "Prompt",
                                style = MaterialTheme.typography.labelLarge,
                                color = White70
                            )
                        },
                        modifier = Modifier.fillMaxSize(),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = DarkGray,
                        ),
                        textStyle = LocalTextStyle.current.copy(color = Color.White)
                    )
                }
            }
            IconButton(
                onClick = {
                    val prompt = promptText.text
                    val itemUser = ChatModel(
                        0, roomId, prompt,
                        prompt,
                        TypeClass.USER, Utils().getCurrentDateTime(dateFormate)
                    )
                    coroutineScope.launch {
                        saveData(itemUser, dataBase, context)
                    }

                    promptText = TextFieldValue("")
                    coroutineScope.launch {
                        try {
                            val response = Utils().generativeModel.generateContent(prompt)
                            response.text?.let {
                                Log.d("Ashu", it)

                                val item = ChatModel(
                                    0, roomId, prompt,
                                    it,
                                    TypeClass.AI, Utils().getCurrentDateTime(dateFormate)
                                )
                                saveData(item, dataBase, context)
                            }
                        } catch (e: Exception) {
                            Log.e("Ashu", "Server error: ${e.message}")
                        }
                        listState.animateScrollToItem(GlobalState.chatList.size - 1)
                    }

                },
                modifier = Modifier
                    .size(60.dp) // Set the size of the button
                    .aspectRatio(1f)
                    .padding(4.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(LightGray, CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Send,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            }
        }
    }


}

suspend fun saveData(item: ChatModel, dataBase: DataBaseName, context: Context) {
    withContext(Dispatchers.Main)
    {

        GlobalState.chatList.add(item)
    }
    val result = dataBase.interfaceDao().insertData(item)
    showToast(context, msg = "$result")
}


