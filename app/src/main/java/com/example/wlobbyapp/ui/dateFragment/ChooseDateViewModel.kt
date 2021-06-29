package com.example.wlobbyapp.ui.dateFragment

import androidx.lifecycle.ViewModel
import com.example.wlobbyapp.data.database.room.dao.WatchedItemDao
import com.example.wlobbyapp.data.database.room.data.WatchedEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChooseDateViewModel (private val dao: WatchedItemDao) : ViewModel() {

    fun insertData(title: String, poster: String, date: String) {
        val data = WatchedEntity(itemTitle = title, photoPath = poster, date = date)
        CoroutineScope(Dispatchers.Main).launch {
            data.let {
                dao.insert(it)
            }
        }

    }
}