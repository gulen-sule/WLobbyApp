package com.example.wlobbyapp.model.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RoomData::class], version = 1)
abstract class WatchedDatabase : RoomDatabase() {
    abstract fun RoomDao(): RoomDao

    companion object {
        @Volatile
        private var instance: WatchedDatabase? = null

        private val lock = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, WatchedDatabase::class.java, "ToDoRoomDatabase").build()

    }
}