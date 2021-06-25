package com.example.wlobbyapp.ui.lobbyFragment

import androidx.lifecycle.ViewModel
import com.example.wlobbyapp.model.Room.RoomDao
import com.example.wlobbyapp.model.Room.RoomData

class LobbyViewModel(dao: RoomDao) : ViewModel() {
    private val dao = dao
    private var list: List<RoomData>? = mutableListOf()

    suspend fun getAllTodo(): List<RoomData>? {
        list = dao.getWatchedAll()
        return list
    }


    suspend fun deleteTodo(position: Int): Int? {
        val itemModel = list?.get(position)
        if (itemModel != null) {
            return itemModel.id?.let { dao.deleteToDo(id = it) }
        }
        return null
    }
}