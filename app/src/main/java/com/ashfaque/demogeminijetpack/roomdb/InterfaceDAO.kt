package com.ashfaque.demogeminijetpack.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ashfaque.demogeminijetpack.Utils.tableName
import com.ashfaque.demogeminijetpack.model.ChatModel
@Dao
interface InterfaceDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(mDataClass: ChatModel):Long

    @Update
    suspend fun updateData(mDataClass: ChatModel):Int

    @Delete
    suspend fun deleteData(mDataClass: ChatModel):Int

    @Query("SELECT * FROM $tableName")
    fun getAllRecord():List<ChatModel>

    @Query("SELECT * FROM $tableName WHERE roomId = :roomId")
    fun getAllRecordByRoomID(roomId:Int):LiveData<List<ChatModel>>


}