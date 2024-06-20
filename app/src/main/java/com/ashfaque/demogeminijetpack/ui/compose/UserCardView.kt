package com.ashfaque.demogeminijetpack.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ashfaque.demogeminijetpack.model.ChatModel
import com.ashfaque.demogeminijetpack.ui.theme.LightGray
import com.ashfaque.demogeminijetpack.ui.theme.White80

@Composable
fun UserCardView(item: ChatModel) {

    Column( modifier = Modifier
        .fillMaxSize()
        .padding(40.dp, 8.dp, 0.dp, 0.dp),
        horizontalAlignment = Alignment.End,
    ) {
        ElevatedCard(shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(
                containerColor = LightGray,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp),
            ) {
                Text(
                    text = item.prompt,
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.White
                )
                Text(
                    text = item.dateTime,
                    style = MaterialTheme.typography.bodySmall,
                    color = White80,
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }

    }

}

