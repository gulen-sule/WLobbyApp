package com.example.wlobbyapp.di

import android.content.Context
import androidx.room.Room
import com.example.wlobbyapp.data.database.room.WatchedDatabase
import com.example.wlobbyapp.data.database.room.dao.WatchedItemDao
import com.example.wlobbyapp.data.database.WatchedItemRepository
import com.example.wlobbyapp.data.database.room.repository.WatchedItemRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
interface AppModule {

    @Binds
    fun bindWatchedItemRepository(watchedItemRepository: WatchedItemRepositoryImpl): WatchedItemRepository
}

@InstallIn(SingletonComponent::class)
@Module
object AppDaoModule {

    @Provides
    fun provideWatchedDao(@ApplicationContext context: Context): WatchedItemDao {
        return WatchedDatabase.invoke(context).databaseDao()
    }

    @Provides
    fun provideWatchedDatabase(@ApplicationContext context: Context): WatchedDatabase {
        return Room.databaseBuilder(context, WatchedDatabase::class.java, "DatabaseRoom").build()
    }

    @Provides
    fun getRepository(dao: WatchedItemDao): WatchedItemRepositoryImpl {
        return WatchedItemRepositoryImpl(dao)
    }

}

