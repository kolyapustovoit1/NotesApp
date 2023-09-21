package com.lux.notes.domain.models

data class Note(
    val id: Int? = null,
    val title: String = "",
    val text: String = "",
    val dateCreated: Long = 0,
)