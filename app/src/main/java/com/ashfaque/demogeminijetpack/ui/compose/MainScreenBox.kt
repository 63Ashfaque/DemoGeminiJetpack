package com.ashfaque.demogeminijetpack.ui.compose

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.sp
import com.ashfaque.demogeminijetpack.Utils
import com.ashfaque.demogeminijetpack.ui.theme.DemoGeminiJetpackTheme
import com.ashfaque.demogeminijetpack.ui.theme.LightGray


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DemoGeminiJetpackTheme {
        MainScreenBox()
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenBox() {

    var searchText by remember { mutableStateOf("") }
    val allItems = remember { Utils().generateRandomStringList(100) }
    val filteredItems = allItems.filter { it.contains(searchText, ignoreCase = true) }


    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(12.dp),
    ) {
        Text(
            text = "WhatsApp Chat Bot", fontSize = 24.sp, color = Color.White,
            modifier = Modifier.padding(16.dp),
        )


        Row(
            modifier = Modifier
                .height(62.dp)
                .padding(top = 8.dp),
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
                Box(modifier = Modifier.padding(20.dp).align(Alignment.CenterHorizontally),
                ) {
                    Text(
                        text = "No data found",fontSize = 24.sp, color = Color.White,
                    )
                }
        }
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            if (filteredItems.isNotEmpty()){
                items(filteredItems) { item ->
                    Text(
                        text = item,fontSize = 24.sp, color = Color.White,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }

    }


}