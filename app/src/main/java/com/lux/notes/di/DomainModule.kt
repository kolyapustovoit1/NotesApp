package com.lux.notes.di

import com.lux.notes.domain.repositories.NotesRepository
import com.lux.notes.domain.repositories.SettingsRepository
import com.lux.notes.domain.usecase.notes.CreateNoteUseCase
import com.lux.notes.domain.usecase.notes.DeleteNoteUseCase
import com.lux.notes.domain.usecase.notes.GetNoteByIdUseCase
import com.lux.notes.domain.usecase.notes.GetNotesUseCase
import com.lux.notes.domain.usecase.settings.GetSettingsUseCase
import com.lux.notes.domain.usecase.settings.SaveSettingsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DomainModule {
    // Notes UseCase
    @Provides
    fun provideCreateNoteUseCase(notesRepository: NotesRepository): CreateNoteUseCase =
        CreateNoteUseCase(notesRepository)

    @Provides
    fun provideDeleteNoteUseCase(notesRepository: NotesRepository): DeleteNoteUseCase =
        DeleteNoteUseCase(notesRepository)

    @Provides
    fun provideGetNoteByIdUseCase(notesRepository: NotesRepository): GetNoteByIdUseCase =
        GetNoteByIdUseCase(notesRepository)

    @Provides
    fun provideGetNotesUseCase(notesRepository: NotesRepository): GetNotesUseCase =
        GetNotesUseCase(notesRepository)

    // Settings UseCase
    @Provides
    fun provideSaveSettingsUseCase(settingsRepository: SettingsRepository): SaveSettingsUseCase =
        SaveSettingsUseCase(settingsRepository)

    @Provides
    fun provideGetSettingsUseCase(settingsRepository: SettingsRepository): GetSettingsUseCase =
        GetSettingsUseCase(settingsRepository)
}