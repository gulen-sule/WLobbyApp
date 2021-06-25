package com.example.wlobbyapp.model.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.util.*

@Dao
interface RoomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: RoomData)

    @Query("SELECT * FROM watched")
    suspend fun getWatchedAll(): List<RoomData>

    @Query("DELETE FROM watched WHERE id=:id ")
    suspend fun deleteToDo(id: Int): Int
}