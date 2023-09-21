package com.lux.notes.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String = "",
    val text: String = "",
    @ColumnInfo(name = "date_created")
    val dateCreated: Long = 0,
)