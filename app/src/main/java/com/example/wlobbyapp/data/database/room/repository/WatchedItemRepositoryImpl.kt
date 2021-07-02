package com.example.wlobbyapp.data.database.room.repository

import android.content.Context
import com.example.wlobbyapp.data.database.WatchedItemRepository
import com.example.wlobbyapp.data.database.room.dao.WatchedItemDao
import com.example.wlobbyapp.data.database.room.data.WatchedEntity
import javax.inject.Inject

class WatchedItemRepositoryImpl @Inject constructor(val dao: WatchedItemDao) : WatchedItemRepository {

    override suspend fun insert(data: WatchedEntity) {

    }

    override suspend fun getWatchedAll(context: Context): List<WatchedEntity> {
        return dao.getWatchedAll()
    }

}