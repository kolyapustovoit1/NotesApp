package com.lux.notes.data.repositories

import com.lux.notes.data.toNote
import com.lux.notes.data.toNoteEntity
import com.lux.notes.data.toNoteList
import com.lux.notes.database.dao.NotesDao
import com.lux.notes.database.entities.NoteEntity
import com.lux.notes.domain.models.Note
import com.lux.notes.domain.repositories.NotesRepository
import dagger.hilt.components.SingletonComponent
import it.czerwinski.android.hilt.annotations.BoundTo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@BoundTo(supertype = NotesRepository::class, component = SingletonComponent::class)
class NotesRepositoryImpl @Inject constructor(
    private val notesDao: NotesDao,
) : NotesRepository {
    override suspend fun insert(note: Note) = notesDao.insertNote(note.toNoteEntity())

    override suspend fun delete(note: Note) = notesDao.deleteNote(note.toNoteEntity())

    override suspend fun getNoteById(noteId: Int): Note? =
        notesDao.getNoteById(noteId)?.toNote()

    override fun getNotes(): Flow<List<Note>> =
        notesDao.getNotes().map { notes: List<NoteEntity> -> notes.toNoteList() }
}