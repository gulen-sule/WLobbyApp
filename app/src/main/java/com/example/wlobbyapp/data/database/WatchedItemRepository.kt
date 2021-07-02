package com.example.wlobbyapp.data.database

import android.content.Context
import com.example.wlobbyapp.data.database.room.data.WatchedEntity


interface WatchedItemRepository {

    suspend fun insert(data: WatchedEntity)

    suspend fun getWatchedAll(context: Context): List<WatchedEntity>

}