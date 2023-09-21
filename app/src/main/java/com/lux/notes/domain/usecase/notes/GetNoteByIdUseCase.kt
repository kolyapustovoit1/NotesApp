package com.lux.notes.domain.usecase.notes

import com.lux.notes.domain.models.Note
import com.lux.notes.domain.repositories.NotesRepository

class GetNoteByIdUseCase(
    private val notesRepository: NotesRepository
) {
    suspend operator fun invoke(noteId: Int): Note? = notesRepository.getNoteById(noteId)
}