package com.ashfaque.demogeminijetpack.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ashfaque.demogeminijetpack.model.ChatModel

@Database(entities = [(ChatModel::class)], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class DataBaseName : RoomDatabase() {
    abstract fun interfaceDao(): InterfaceDAO
    companion object {
        @Volatile
        private var INSTANCE: DataBaseName? = null

        fun getDataBase(context: Context): DataBaseName {
            // only one thread of execution at a time can enter this block of code
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DataBaseName::class.java,
                        "ChatBotDB"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}