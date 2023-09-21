package com.lux.notes.data

import com.lux.notes.database.entities.NoteEntity
import com.lux.notes.domain.models.Note

fun NoteEntity.toNote(): Note =
    Note(
        id = id,
        title = title,
        text = text,
        dateCreated = dateCreated,
    )

fun Note.toNoteEntity(): NoteEntity =
    NoteEntity(
        id = id,
        title = title,
        text = text,
        dateCreated = dateCreated,
    )

fun List<NoteEntity>.toNoteList(): List<Note> =
    this.map { noteEntity -> noteEntity.toNote() }