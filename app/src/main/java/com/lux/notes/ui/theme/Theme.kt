package com.lux.notes.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lux.notes.presentation.screens.settings.SettingsViewModel

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFFFA800),
    onPrimary = Color(0xFFF0F0F0),
    onSecondary = Color(0xFFE9E9E9),
    background = Color(0xFF1A1A1A),
    onBackground = Color(0xFFE8E8E8),
    primaryContainer = Color(0xFF303030),
    onPrimaryContainer = Color(0xFFF0F0F0),
    surface = Color(0xFF1A1A1A)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFFFC01F),
    onPrimary = Color(0xFFF0F0F0),
    onSecondary = Color(0xFF49454F),
    background = Color(0xFFF0F0F0),
    onBackground = Color(0xFF1D1B20),
    primaryContainer = Color(0xFFFCFCFC),
    onPrimaryContainer = Color(0xFF1D1B20),
    surface = Color(0xFFF0F0F0)
)

enum class ThemeMode {
    Light,
    Dark,
    ;

    fun toggle(): ThemeMode = when (this) {
        Light -> Dark
        Dark -> Light
    }
}

enum class UiMode {
    List,
    Tiles,
    ;

    fun toggle(): UiMode = when (this) {
        List -> Tiles
        Tiles -> List
    }
}

val LocalThemeMode = staticCompositionLocalOf<MutableState<ThemeMode>> {
    error("ThemeMode not provided")
}

val LocalUiMode = staticCompositionLocalOf<MutableState<UiMode>> {
    error("UiMode not provided")
}

@Composable
fun NotesTheme(
    settingsViewModel: SettingsViewModel = hiltViewModel(),
    content: @Composable () -> Unit
) {
    val darkTheme = settingsViewModel.isDarkTheme.value
    val listView = settingsViewModel.isListView.value

    val themeMode = remember { mutableStateOf(if (darkTheme) ThemeMode.Dark else ThemeMode.Light) }
    val uiMode = remember { mutableStateOf(if (listView) UiMode.List else UiMode.Tiles) }

    CompositionLocalProvider(
        LocalThemeMode provides themeMode,
        LocalUiMode provides uiMode
    ) {
        val colorScheme = when (LocalThemeMode.current.value) {
            ThemeMode.Light -> LightColorScheme
            ThemeMode.Dark -> DarkColorScheme
        }

        val systemUiController = rememberSystemUiController()
        SideEffect {
            systemUiController.setStatusBarColor(
                color = colorScheme.background,
                darkIcons = true
            )
        }

        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}