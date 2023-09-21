package com.lux.notes.data.repositories

import com.lux.notes.data.preferences.PreferencesManager
import com.lux.notes.domain.models.SettingsData
import com.lux.notes.domain.repositories.SettingsRepository
import dagger.hilt.components.SingletonComponent
import it.czerwinski.android.hilt.annotations.BoundTo
import javax.inject.Inject

@BoundTo(supertype = SettingsRepository::class, component = SingletonComponent::class)
class SettingsRepositoryImpl @Inject constructor(
    private val preferencesManager: PreferencesManager
) : SettingsRepository {
    override fun saveSettings(settingsData: SettingsData) {
        preferencesManager.save(settingsData)
    }

    override fun getSettings(): SettingsData = preferencesManager.get()

}