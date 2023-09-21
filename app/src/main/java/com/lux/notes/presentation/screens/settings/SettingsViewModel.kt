package com.lux.notes.presentation.screens.settings

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lux.notes.domain.models.SettingsData
import com.lux.notes.domain.usecase.settings.GetSettingsUseCase
import com.lux.notes.domain.usecase.settings.SaveSettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val saveSettingsUseCase: SaveSettingsUseCase,
    private val getSettingsUseCase: GetSettingsUseCase
) : ViewModel() {
    private val _isDarkTheme = mutableStateOf(false)
    val isDarkTheme: State<Boolean> = _isDarkTheme

    private val _isListView = mutableStateOf(false)
    val isListView: State<Boolean> = _isListView

    init {
        viewModelScope.launch {
            getSettingsUseCase().let {
                _isDarkTheme.value = it.darkTheme
                _isListView.value = it.listView
            }
        }
    }

    fun onDarkThemeChanged(value: Boolean) {
        _isDarkTheme.value = value
        saveSettings()
    }

    fun onListViewChanged(value: Boolean) {
        _isListView.value = value
        saveSettings()
    }

    private fun saveSettings() {
        saveSettingsUseCase(
            SettingsData(
                listView = _isListView.value,
                darkTheme = _isDarkTheme.value
            )
        )
    }
}