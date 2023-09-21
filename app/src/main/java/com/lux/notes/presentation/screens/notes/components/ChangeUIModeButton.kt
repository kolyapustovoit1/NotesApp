package com.lux.notes.presentation.screens.notes.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import com.lux.notes.ui.theme.UiMode

@Composable
fun ChangeUIModeButton(
    uiMode: UiMode,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        when (uiMode) {
            UiMode.Tiles -> Icon(Icons.Outlined.List, "List UI mode")
            UiMode.List -> Icon(Icons.Outlined.Star, "Tiles UI mode")
        }
    }
}