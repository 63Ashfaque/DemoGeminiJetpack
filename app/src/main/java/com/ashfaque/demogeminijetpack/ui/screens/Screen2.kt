package com.ashfaque.demogeminijetpack.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ashfaque.demogeminijetpack.Utils.GlobalState
import com.ashfaque.demogeminijetpack.R
import com.ashfaque.demogeminijetpack.model.ChatModel
import com.ashfaque.demogeminijetpack.roomdb.DataBaseName
import com.ashfaque.demogeminijetpack.ui.compose.showToast
import com.ashfaque.demogeminijetpack.ui.theme.DarkGray
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen2(navController: NavHostController,roomId: String) {
    val context = LocalContext.current
    val dataBase = DataBaseName.getDataBase(context)
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
                        Text(text = roomId,style = MaterialTheme.typography.labelSmall)
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
                ChatScreen(GlobalState.chatList,roomId)
                //ChatScreen(result,roomId)
            }
        }
    }
}