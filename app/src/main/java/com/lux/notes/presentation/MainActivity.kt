package com.lux.notes.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection
import androidx.compose.animation.core.tween
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lux.notes.presentation.screens.add_edit_note.AddEditNoteScreen
import com.lux.notes.presentation.screens.notes.NotesScreen
import com.lux.notes.presentation.screens.settings.SettingsScreen
import com.lux.notes.presentation.utils.NavDestinations
import com.lux.notes.ui.theme.NotesTheme
import dagger.hilt.android.AndroidEntryPoint

private const val NavAnimationDuration = 400

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val navController = rememberNavController()

            NotesTheme {
                NavHost(
                    navController = navController,
                    startDestination = NavDestinations.NotesScreen.route
                ) {

                    composable(
                        NavDestinations.NotesScreen.route,
                        enterTransition = {
                            slideIntoContainer(SlideDirection.End, animationSpec = tween(NavAnimationDuration))
                        },
                        exitTransition = {
                            slideOutOfContainer(SlideDirection.Start, animationSpec = tween(NavAnimationDuration))
                        }
                    ) {
                        NotesScreen(navController)
                    }

                    composable(
                        NavDestinations.SettingsScreen.route,
                        enterTransition = {
                            // Let's make for a really long fade in
                            slideIntoContainer(SlideDirection.Start, animationSpec = tween(NavAnimationDuration))
                        },
                        exitTransition = {
                            slideOutOfContainer(SlideDirection.End, animationSpec = tween(NavAnimationDuration))
                        }
                    ) {
                        SettingsScreen(navController)
                    }

                    composable(
                        route = NavDestinations.AddEditNoteScreen.route,
                        arguments = NavDestinations.AddEditNoteScreen.args,
                        enterTransition = {
                            // Let's make for a really long fade in
                            slideIntoContainer(SlideDirection.Start, animationSpec = tween(NavAnimationDuration))
                        },
                        exitTransition = {
                            slideOutOfContainer(SlideDirection.End, animationSpec = tween(NavAnimationDuration))
                        }
                    ) {
                        AddEditNoteScreen(
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}
