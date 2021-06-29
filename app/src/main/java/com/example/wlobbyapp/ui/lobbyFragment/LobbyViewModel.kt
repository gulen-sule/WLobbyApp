package com.example.wlobbyapp.ui.lobbyFragment

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.wlobbyapp.data.database.room.data.WatchedEntity
import com.example.wlobbyapp.data.database.room.repository.WatchedItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LobbyViewModel @Inject constructor(private val watchedItemRepository: WatchedItemRepository) : ViewModel() {
    private var list: List<WatchedEntity>? = arrayListOf()

    suspend fun getAllTodo(context: Context): List<WatchedEntity>? {
        list = watchedItemRepository.getWatchedAll(context)
        return list
    }

//
//    suspend fun deleteTodo(position: Int): Int? {
//        val itemModel = list?.get(position)
//        if (itemModel != null) {
//            return itemModel.id?.let { dao.deleteToDo(id = it) }
//        }
//        return null
//    }
}