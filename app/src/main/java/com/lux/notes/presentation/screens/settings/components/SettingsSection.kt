package com.lux.notes.presentation.screens.settings.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@Composable
fun SettingsSection(
    title: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(),
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            style = textStyle
        )
        content()
    }
}