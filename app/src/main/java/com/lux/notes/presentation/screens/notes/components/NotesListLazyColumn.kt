package com.lux.notes.presentation.screens.notes.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lux.notes.domain.models.Note
import com.lux.notes.presentation.utils.NavDestinations
import com.lux.notes.ui.theme.PlaceholderColor

@Composable
fun NotesListLazyColumn(
    items: List<Note>,
    navController: NavController
) {
    LazyColumn(
        modifier = Modifier
            .padding()
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (items.isNotEmpty()) {
            items(items) { note ->
                NoteItem(
                    note = note,
                    modifier = Modifier.padding(15.dp, 2.dp, 15.dp, 13.dp),
                    colors = NoteItemColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleColor = if (note.title.isEmpty()) {
                            PlaceholderColor
                        } else {
                            MaterialTheme.colorScheme.onPrimaryContainer
                        },
                        contentColor = MaterialTheme.colorScheme.onSecondary,
                        dateColor = PlaceholderColor
                    ),
                    onClick = {
                        NavDestinations.AddEditNoteScreen.getRoute(
                            arguments = NavDestinations.AddEditNoteScreen.Arguments(noteId = note.id)
                        ).let { navController.navigate(it) }
                    }
                )
            }
        }
    }
}

