package com.lux.notes.presentation.screens.notes.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.lux.notes.R
import com.lux.notes.ui.theme.PlaceholderColor

@Preview
@Composable
fun NotesNotFoundText() {
    Text(
        text = "Notes not found.\nCreate a new note",
        textAlign = TextAlign.Center,
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        color = PlaceholderColor
    )
}