package com.ashfaque.demogeminijetpack.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.ashfaque.demogeminijetpack.Utils.TypeClass
import com.ashfaque.demogeminijetpack.Utils.tableName

@Entity(tableName = tableName)
data class ChatModel(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val roomId:String,
    val roomName:String,
    val prompt: String,
    val type: TypeClass,
    val dateTime: String
)


data class ChatListModel(

    val roomId:String,
    val roomName:String,
)