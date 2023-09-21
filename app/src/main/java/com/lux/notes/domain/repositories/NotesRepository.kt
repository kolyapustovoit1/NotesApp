package com.lux.notes.domain.repositories

import com.lux.notes.domain.models.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    suspend fun insert(note: Note)
    suspend fun delete(note: Note)
    suspend fun getNoteById(noteId: Int): Note?
    fun getNotes(): Flow<List<Note>>
}