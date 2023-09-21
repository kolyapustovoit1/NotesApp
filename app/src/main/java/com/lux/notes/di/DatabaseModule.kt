package com.lux.notes.di

import android.content.Context
import androidx.room.Room
import com.lux.notes.database.Database
import com.lux.notes.database.entities.NoteEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            Database::class.java,
            "notes_database"
        ).build()

    @Provides
    fun provideNotesDao(database: Database) = database.getNotesDao()

    @Provides
    fun provideNoteEntity() = NoteEntity()
}