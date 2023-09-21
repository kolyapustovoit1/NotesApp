package com.lux.notes.presentation.screens.settings.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun SettingsSwitchItemPreview() {
    var checkedDarkTheme by remember { mutableStateOf(false) }
    var checkedListView by remember { mutableStateOf(false) }

    SettingsSection(title = "Main") {
        SettingsSwitchItem(
            title = "Dark theme",
            checked = checkedDarkTheme,
            onCheckedChange = { checkedDarkTheme = it }
        )
        SettingsSwitchItem(
            title = "List view",
            checked = checkedListView,
            onCheckedChange = { checkedListView = it }
        )
    }
}

@Composable
fun SettingsSwitchItem(
    title: String,
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    textStyle: TextStyle = TextStyle()
) {
    val interactionSource = remember { MutableInteractionSource() }

    Box(modifier = modifier.clickable(
        interactionSource = interactionSource,
        indication = null
    ) { onCheckedChange(!checked) }) {
        Text(
            text = title,
            modifier = Modifier.fillMaxWidth().align(Alignment.CenterStart),
            style = textStyle
        )
        Switch(
            modifier = Modifier.align(Alignment.CenterEnd),
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}