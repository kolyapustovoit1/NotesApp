package com.lux.notes.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lux.notes.database.dao.NotesDao
import com.lux.notes.database.entities.NoteEntity

@Database(
    version = 1,
    entities = [
        NoteEntity::class
    ]
)
abstract class Database : RoomDatabase() {
    abstract fun getNotesDao(): NotesDao
}