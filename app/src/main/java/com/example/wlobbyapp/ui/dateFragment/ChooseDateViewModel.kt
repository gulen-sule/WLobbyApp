package com.example.wlobbyapp.ui.dateFragment

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.wlobbyapp.model.Room.RoomDao
import com.example.wlobbyapp.model.Room.RoomData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChooseDateViewModel(dao: RoomDao) : ViewModel() {
    private val dao: RoomDao = dao

    fun insertData(title: String, poster: String, date: String) {
        val data = RoomData(itemTitle = title, photoPath = poster, date = date)
        CoroutineScope(Dispatchers.Main).launch {
            data.let {
                dao.insert(it)
            }
        }

    }
}