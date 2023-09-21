package com.lux.notes.di

import android.content.Context
import com.lux.notes.data.preferences.PreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule {
    @Provides
    @Singleton
    fun provideSettingsDataStoreManager(
        @ApplicationContext context: Context
    ): PreferencesManager =
        PreferencesManager(context)
}