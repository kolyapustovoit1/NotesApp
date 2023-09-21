package com.lux.notes.domain.usecase.notes

import com.lux.notes.domain.models.Note
import com.lux.notes.domain.repositories.NotesRepository

class CreateNoteUseCase(
    private val notesRepository: NotesRepository
) {
    suspend operator fun invoke(note: Note) = notesRepository.insert(note)
}