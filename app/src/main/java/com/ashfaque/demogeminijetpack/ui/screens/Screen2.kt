package com.ashfaque.demogeminijetpack.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ashfaque.demogeminijetpack.Utils.GlobalState
import com.ashfaque.demogeminijetpack.R
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen2(navController: NavHostController,roomId: String) {

    var isTyping = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val dataBase = DataBaseName.getDataBase(context)

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
   // var result by remember { mutableStateOf<List<ChatModel>>(GlobalState.chatList) }
    // Fetch data from the database in a coroutine
    LaunchedEffect(Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val records = dataBase.interfaceDao().getAllRecordByRoomID(roomId)
           // result = records
            GlobalState.chatList.addAll(records)
        }
    }

    // Handle back press
    BackHandler {
        // Clear the chatList
        GlobalState.chatList.clear()
        navController.popBackStack()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(text = roomId,style = MaterialTheme.typography.titleSmall)
                        Text(text = if(isTyping.value) "Typing..." else "",style = MaterialTheme.typography.labelSmall)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DarkGray, // Change the background color here
                    titleContentColor = Color.White, // Change the title text color here
                    navigationIconContentColor = Color.White // Change the navigation icon color here
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        GlobalState.chatList.clear()
                        navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.End
            ) {
               // ChatScreen(GlobalState.chatList,roomId)
                //ChatScreen(result,roomId)
                val items=GlobalState.chatList
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
                                isTyping.value=true
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
                                    isTyping.value=false
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
        }
    }
}


