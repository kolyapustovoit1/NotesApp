package com.lux.notes.presentation.screens.add_edit_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lux.notes.domain.models.Note
import com.lux.notes.domain.usecase.notes.CreateNoteUseCase
import com.lux.notes.domain.usecase.notes.DeleteNoteUseCase
import com.lux.notes.domain.usecase.notes.GetNoteByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val createNoteUseCase: CreateNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _noteTitleState = mutableStateOf("")
    val noteTitleState: State<String> = _noteTitleState

    private val _noteTextState = mutableStateOf("")
    val noteTextState: State<String> = _noteTextState

    private val _noteDateCreatedState = mutableLongStateOf(System.currentTimeMillis())
    val noteDateCreatedState: State<Long> = _noteDateCreatedState

    private val _isCreateNote = mutableStateOf(false)
    val isCreateNote: State<Boolean> = _isCreateNote

    private val _saveButtonEnabled = mutableStateOf(false)
    val saveButtonEnabled: State<Boolean> = _saveButtonEnabled

    private var currentNoteId: Int? = null

    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if(noteId != -1) {
                viewModelScope.launch {
                    getNoteByIdUseCase(noteId)?.also { note ->
                        currentNoteId = note.id
                        _noteTitleState.value = note.title
                        _noteTextState.value = note.text
                        _noteDateCreatedState.longValue = note.dateCreated
                    }
                }
            } else {
                _isCreateNote.value = true
            }
        }
    }

    private fun onSaveButtonEnabled() { if (!_saveButtonEnabled.value) _saveButtonEnabled.value = true }

    private fun onSaveButtonDisabled() { if (_saveButtonEnabled.value) _saveButtonEnabled.value = false }

    fun onNoteTitleChanged(value: String) {
        _noteTitleState.value = value
        onSaveButtonEnabled()
    }

    fun onNoteTextChanged(value: String) {
        _noteTextState.value = value
        onSaveButtonEnabled()
    }

    fun saveNote() {
        viewModelScope.launch {
            if (_noteTitleState.value.isNotEmpty() || _noteTextState.value.isNotEmpty()) {
                _noteDateCreatedState.longValue = System.currentTimeMillis()
                createNoteUseCase(
                    Note(
                        id = currentNoteId,
                        title = _noteTitleState.value,
                        text = _noteTextState.value,
                        dateCreated = _noteDateCreatedState.longValue,
                    )
                )
            }
        }
        onSaveButtonDisabled()
    }

    fun deleteNote() {
        viewModelScope.launch {
            deleteNoteUseCase(
                Note(
                    id = currentNoteId,
                    title = _noteTitleState.value,
                    text = _noteTextState.value,
                    dateCreated = _noteDateCreatedState.longValue,
                )
            )
        }
    }
}