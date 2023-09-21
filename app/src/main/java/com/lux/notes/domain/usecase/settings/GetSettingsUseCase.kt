package com.lux.notes.domain.usecase.settings

import com.lux.notes.domain.models.SettingsData
import com.lux.notes.domain.repositories.SettingsRepository
import kotlinx.coroutines.flow.Flow

class GetSettingsUseCase(
    private val settingsRepository: SettingsRepository
) {
    operator fun invoke(): SettingsData = settingsRepository.getSettings()
}