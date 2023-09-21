package com.lux.notes.domain.usecase.notes

import com.lux.notes.domain.models.Note
import com.lux.notes.domain.repositories.NotesRepository
import kotlinx.coroutines.flow.Flow

class GetNotesUseCase(
    private val notesRepository: NotesRepository
) {
    operator fun invoke(): Flow<List<Note>> = notesRepository.getNotes()
}