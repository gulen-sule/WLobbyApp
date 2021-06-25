package com.example.wlobbyapp.model.Room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "watched")
data class RoomData(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    @ColumnInfo(name = "itemTitle")
    var itemTitle: String?,

    @ColumnInfo(name = "photoPath")
    var photoPath: String?,

    @ColumnInfo(name = "date")
    var date: String?

) : Serializable
