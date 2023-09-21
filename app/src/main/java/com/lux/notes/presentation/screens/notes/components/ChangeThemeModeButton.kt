package com.lux.notes.presentation.screens.notes.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.lux.notes.R
import com.lux.notes.ui.theme.ThemeMode

@Composable
fun ChangeThemeModeButton(
    themeMode: ThemeMode,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        val darkModeIconPainter = painterResource(id = R.drawable.outline_dark_mode)
        val lightModeIconPainter = painterResource(id = R.drawable.outline_light_mode)

        when (themeMode) {
            ThemeMode.Light -> Icon(darkModeIconPainter, "Change theme")
            ThemeMode.Dark -> Icon(lightModeIconPainter, "Change theme")
        }
    }
}