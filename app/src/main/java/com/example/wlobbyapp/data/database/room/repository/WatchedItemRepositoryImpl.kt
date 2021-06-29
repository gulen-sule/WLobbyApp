package com.example.wlobbyapp.data.database.room.repository

import android.content.Context
import android.util.Log
import com.example.wlobbyapp.data.database.room.dao.WatchedItemDao
import com.example.wlobbyapp.data.database.room.data.WatchedEntity
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Inject

class WatchedItemRepositoryImpl @Inject constructor(val dao: WatchedItemDao) : WatchedItemRepository {

    override suspend fun insert(data: WatchedEntity) {

    }

    override suspend fun getWatchedAll(context: Context): List<WatchedEntity> {
        return dao.getWatchedAll()
    }

}