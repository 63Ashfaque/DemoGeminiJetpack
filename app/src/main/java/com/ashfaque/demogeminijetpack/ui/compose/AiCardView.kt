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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ashfaque.demogeminijetpack.model.ChatModel
import com.ashfaque.demogeminijetpack.ui.theme.DarkGray
import com.ashfaque.demogeminijetpack.ui.theme.White80
import dev.jeziellago.compose.markdowntext.MarkdownText


@Composable
fun AiCardView(item: ChatModel) {

    Column( modifier = Modifier
        .fillMaxSize()
        .padding(0.dp, 8.dp, 40.dp, 0.dp),
    ) {
        ElevatedCard(shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(
                containerColor = DarkGray,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                MarkdownText(
                    modifier = Modifier.padding(8.dp),
                    markdown = item.prompt,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 14.sp,
                        lineHeight = 10.sp,
                        textAlign = TextAlign.Justify,
                    ),

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
