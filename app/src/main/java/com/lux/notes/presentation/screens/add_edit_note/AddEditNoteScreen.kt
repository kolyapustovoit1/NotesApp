package com.lux.notes.presentation.screens.add_edit_note

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lux.notes.presentation.components.TopBar
import com.lux.notes.presentation.screens.add_edit_note.components.TransparentTextField
import com.lux.notes.presentation.utils.toLocalDateTime
import com.lux.notes.ui.theme.NoteTextStyle
import com.lux.notes.ui.theme.NoteTitleTextStyle
import com.lux.notes.ui.theme.SubtitleTextStyle
import java.time.format.DateTimeFormatter

@Composable
fun AddEditNoteScreen(
    navController: NavController,
    viewModel: AddEditNoteViewModel = hiltViewModel()
) {
    val imeVisible by keyboardAsState()

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    val title by viewModel.noteTitleState
    val text by viewModel.noteTextState
    val dateCreated by viewModel.noteDateCreatedState

    val isCreateNote by viewModel.isCreateNote
    val saveButtonEnabled by viewModel.saveButtonEnabled

    Log.d("isCreate", "isCreateNote - $isCreateNote")

    LaunchedEffect(Unit) {
        if (text.isEmpty() && !imeVisible) focusRequester.requestFocus()
    }

    Scaffold(
        topBar = {
            TopBar(
                isBackIcon = true,
                onBackIconClick = {
                    navController.navigateUp()
                }, actions = {
                    // Save Button
                    IconButton(
                        enabled = saveButtonEnabled,
                        onClick = {
                            viewModel.saveNote()
                            focusManager.clearFocus()
                            if (isCreateNote) navController.navigateUp()
                        }) {
                        Icon(Icons.Outlined.Check, "Save note")
                    }

                    // Delete Button
                    IconButton(onClick = {
                        viewModel.deleteNote()
                        navController.navigateUp()
                    }) {
                        Icon(Icons.Outlined.Delete, "Delete note")
                    }
                })
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .imePadding()
        ) {
            // Title
            TransparentTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 0.dp),
                value = title,
                hint = "Title",
                onValueChange = {
                    viewModel.onNoteTitleChanged(it)
                },
                textStyle = NoteTitleTextStyle,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                })
            )

            // Subtitle row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp, 0.dp)
            ) {
                Text(
                    text = dateCreated.toLocalDateTime().format(
                        DateTimeFormatter.ofPattern("dd.MM.yyy HH:mm")
                    ),
                    modifier = Modifier.padding(0.dp, 0.dp, 10.dp, 0.dp),
                    style = SubtitleTextStyle
                )
                HorizontalDivider(
                    color = Color(0x80686868),
                    modifier = Modifier
                        .height(10.dp)
                        .width(1.dp)
                        .align(Alignment.CenterVertically)
                )
                Text(
                    text = text.filter {
                        it.isLetterOrDigit()
                    }.length.toString() + " characters",
                    modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp),
                    style = SubtitleTextStyle
                )
            }

            TransparentTextField(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp, 0.dp)
                    .focusRequester(focusRequester),
                value = text,
                hint = "Start typing",
                onValueChange = {
                    viewModel.onNoteTextChanged(it)
                },
                textStyle = NoteTextStyle
            )
        }
    }
}

@Composable
fun keyboardAsState(): State<Boolean> {
    val isImeVisible = WindowInsets.ime.getBottom(LocalDensity.current) > 0
    return rememberUpdatedState(isImeVisible)
}