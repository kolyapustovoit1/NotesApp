package com.lux.notes.domain.repositories

import com.lux.notes.domain.models.SettingsData
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    fun saveSettings(settingsData: SettingsData)
    fun getSettings(): SettingsData
}