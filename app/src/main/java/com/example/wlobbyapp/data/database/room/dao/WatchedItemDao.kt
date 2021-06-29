package com.example.wlobbyapp.data.database.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wlobbyapp.data.database.room.data.WatchedEntity

@Dao
interface WatchedItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: WatchedEntity)

    @Query("SELECT * FROM watchedItemModel ")
    suspend fun getWatchedAll(): List<WatchedEntity>

    @Query("DELETE FROM watchedItemModel WHERE id=:id ")
    suspend fun deleteToDo(id: Int): Int
}