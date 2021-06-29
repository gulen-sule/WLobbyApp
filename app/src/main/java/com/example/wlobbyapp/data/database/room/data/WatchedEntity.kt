package com.example.wlobbyapp.data.database.room.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "watchedItemModel")
data class WatchedEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    @ColumnInfo(name = "itemTitle")
    var itemTitle: String?,

    @ColumnInfo(name = "photoPath")
    var photoPath: String?,

    @ColumnInfo(name = "date")
    var date: String?

) : Serializable
