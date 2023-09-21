package com.lux.notes.presentation.screens.notes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lux.notes.presentation.components.TopBar
import com.lux.notes.presentation.screens.notes.components.CreateNoteFAB
import com.lux.notes.presentation.screens.notes.components.NotesListLazyColumn
import com.lux.notes.presentation.screens.notes.components.NotesListLazyGrid
import com.lux.notes.presentation.screens.notes.components.NotesNotFoundText
import com.lux.notes.presentation.utils.NavDestinations
import com.lux.notes.ui.theme.LocalUiMode
import com.lux.notes.ui.theme.UiMode

@Composable
fun NotesScreen(
    navController: NavController,
    viewModel: NotesViewModel = hiltViewModel()
) {
    val notes = viewModel.notesState.value

    Scaffold(
        topBar = {
            TopBar(
                title = "Notes",
                actions = {
                    IconButton(onClick = { navController.navigate(NavDestinations.SettingsScreen.route) }) {
                        Icon(Icons.Outlined.Settings, "App settings")
                    }
                }
            )
        },
        floatingActionButton = {
            CreateNoteFAB(
                modifier = Modifier
                    .offset((-15).dp, (-15).dp)
                    .size(60.dp),
                onClick = { navController.navigate(NavDestinations.AddEditNoteScreen.route) }
            )
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (notes.isEmpty()) {
                true -> NotesNotFoundText()
                false -> {
                    if (LocalUiMode.current.value == UiMode.List) {
                        NotesListLazyColumn(notes, navController)
                    } else {
                        NotesListLazyGrid(notes, navController)
                    }
                }
            }
        }
    }
}











