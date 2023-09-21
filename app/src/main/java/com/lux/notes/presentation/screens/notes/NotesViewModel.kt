package com.lux.notes.presentation.screens.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lux.notes.domain.models.Note
import com.lux.notes.domain.usecase.notes.GetNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase
) : ViewModel() {
    private val _notesState = mutableStateOf(emptyList<Note>())
    val notesState: State<List<Note>> = _notesState

    private var notesJob: Job? = null

    init { getNotes() }

    private fun getNotes() {
        notesJob?.cancel()
        notesJob = getNotesUseCase()
            .onEach {
                _notesState.value = it
            }
            .launchIn(viewModelScope)
    }
}