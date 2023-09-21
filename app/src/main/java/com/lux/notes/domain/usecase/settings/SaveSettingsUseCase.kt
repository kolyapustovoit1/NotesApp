package com.lux.notes.domain.usecase.settings

import com.lux.notes.domain.models.SettingsData
import com.lux.notes.domain.repositories.SettingsRepository

class SaveSettingsUseCase (
    private val settingsRepository: SettingsRepository
) {
    operator fun invoke(settingsData: SettingsData) {
        settingsRepository.saveSettings(settingsData)
    }
}