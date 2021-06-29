package com.example.wlobbyapp.data.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.wlobbyapp.data.database.room.dao.WatchedItemDao
import com.example.wlobbyapp.data.database.room.data.WatchedEntity


@Database(entities = [WatchedEntity::class], version = 1)
abstract class WatchedDatabase : RoomDatabase() {
    abstract fun databaseDao(): WatchedItemDao

    companion object {
        @Volatile
        var instance: WatchedDatabase? = null

        val lock = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        fun makeDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, WatchedDatabase::class.java, "ToDoRoomDatabase").build()

    }
}