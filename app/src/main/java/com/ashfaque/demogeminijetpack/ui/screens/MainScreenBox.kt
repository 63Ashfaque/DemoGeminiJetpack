package com.ashfaque.demogeminijetpack.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ashfaque.demogeminijetpack.Utils.Utils
import com.ashfaque.demogeminijetpack.ui.theme.DarkGray
import com.ashfaque.demogeminijetpack.ui.theme.LightGray
import com.ashfaque.demogeminijetpack.ui.theme.White70
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {

    var searchText by remember { mutableStateOf("") }
    val allItems = remember { Utils().generateRandomStringList(100) }
    val filteredItems = allItems.filter { it.contains(searchText, ignoreCase = true) }

    Scaffold(
        modifier=Modifier.background(DarkGray),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("screen2") },
                contentColor = DarkGray,
                shape = CircleShape,
            ) {
                Icon(Icons.Filled.Add, "Floating action button.",Modifier.size(40.dp))
            }
        },
        floatingActionButtonPosition = FabPosition.End
        ){Column(
        modifier = Modifier
            .background(DarkGray)
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(it),
    ) {
        Text(
            text = "WhatsApp Chat Bot", fontSize = 24.sp, color = Color.White,
            modifier = Modifier.padding(16.dp),
        )

        Row(
            modifier = Modifier
                .height(70.dp)
                .padding(8.dp),
        ) {
            ElevatedCard(
                shape = RoundedCornerShape(25.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                ),
                modifier = Modifier.weight(0.7f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {

                    TextField(
                        value = searchText,
                        onValueChange = { searchText = it },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "",
                                tint = Color.White
                            )
                        },
                        placeholder = {
                            Text(
                                text = "Search",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.White
                            )
                        },
                        modifier = Modifier.fillMaxSize(),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = LightGray,
                        ),
                        textStyle = LocalTextStyle.current.copy(color = Color.White)
                    )
                }
            }
        }


        Spacer(modifier = Modifier.height(8.dp))
        if (filteredItems.isEmpty()) {
            Box(
                modifier = Modifier
                    .padding(20.dp)
                    .align(Alignment.CenterHorizontally),
            ) {
                Text(
                    text = "No data found", fontSize = 24.sp, color = Color.White,
                )
            }
        }
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            if (filteredItems.isNotEmpty()) {
                items(filteredItems) { item ->
                    Row(
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable { navController.navigate("screen2") },
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(60.dp)
                                .background(LightGray, shape = CircleShape)
                                .padding(8.dp)
                                .align(Alignment.CenterVertically),
                        ) {
                            Text(
                                text = item.substring(0, 1).uppercase(Locale.getDefault()),
                                fontSize = 30.sp,
                                color = Color.White,
                            )
                        }
                        Column {

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = item, fontSize = 24.sp, color = Color.White,
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                                Text(
                                    text = item, fontSize = 16.sp, color = White70,
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                            }

                            Text(
                                text = item, fontSize = 16.sp, color = White70,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }


                    }
                }
            }
        }

    }
    }

}