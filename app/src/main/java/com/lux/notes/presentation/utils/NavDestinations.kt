package com.lux.notes.presentation.utils

import android.os.Bundle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class NavDestinations(
    val route: String,
    val args: List<NamedNavArgument> = emptyList()
) {
    object NotesScreen : NavDestinations("notes")

    object SettingsScreen : NavDestinations("settings")

    object AddEditNoteScreen : NavDestinations(
        route = "add_edit_note?noteId={noteId}",
        args = listOf(
            navArgument("noteId") {
                type = NavType.IntType
                defaultValue = -1
            }
        )
    ) {
        class Arguments(val noteId: Int?)

        fun getRoute(arguments: Arguments): String {
            return "${AddEditNoteScreen.route.split("?")[0]}?noteId=${arguments.noteId}"
        }
        fun getArguments(bundle: Bundle): Arguments {
            return Arguments(
                noteId = bundle.getInt("noteId")
            )
        }
    }
}
