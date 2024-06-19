package com.ashfaque.demogeminijetpack.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.CardDefaults
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ashfaque.demogeminijetpack.ChatModel
import com.ashfaque.demogeminijetpack.GlobalState.chatList
import com.ashfaque.demogeminijetpack.TypeClass
import com.ashfaque.demogeminijetpack.Utils
import com.ashfaque.demogeminijetpack.dateFormate
import com.ashfaque.demogeminijetpack.ui.theme.DarkGray

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PromptBar() {
    var promptText by remember { mutableStateOf(TextFieldValue("")) }

    Row( modifier = Modifier
        .height(60.dp)
        .padding(top = 8.dp),
    ) {
        ElevatedCard(shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
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
                            color = Color.White
                        )},
                    modifier = Modifier.fillMaxSize(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = DarkGray,
                    ),
                    textStyle = LocalTextStyle.current.copy(color = Color.White)
                )
            }
        }
        IconButton(
            onClick =  {
                chatList.add(ChatModel(promptText.text, TypeClass.USER, Utils().getCurrentDateTime(dateFormate)))
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
                    .background(DarkGray, CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription="",
                    tint = Color.White
                )
            }
        }
    }
}
