package com.lux.notes.presentation.screens.notes.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lux.notes.domain.models.Note
import com.lux.notes.presentation.utils.NavDestinations
import com.lux.notes.ui.theme.PlaceholderColor

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotesListLazyGrid(
    items: List<Note>,
    navController: NavController
) {
    // Set paddings
    val lazyGridPadding = PaddingValues(0.dp, 0.dp, 15.dp, 0.dp)
    val notesCardPadding = PaddingValues(15.dp, 2.dp, 0.dp, 13.dp)

    // Layout
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier
            .padding(lazyGridPadding)
            .fillMaxSize()
    ) {
        if (items.isNotEmpty()) {
            items(items) { note ->
                NoteItem(
                    note = note,
                    modifier = Modifier.padding(notesCardPadding),
                    noteTextMaxLine = 4,
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