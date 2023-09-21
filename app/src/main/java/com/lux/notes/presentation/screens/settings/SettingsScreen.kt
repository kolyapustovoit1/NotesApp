package com.lux.notes.presentation.screens.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lux.notes.presentation.components.TopBar
import com.lux.notes.presentation.screens.settings.components.SettingsSection
import com.lux.notes.presentation.screens.settings.components.SettingsSwitchItem
import com.lux.notes.ui.theme.LocalThemeMode
import com.lux.notes.ui.theme.LocalUiMode
import com.lux.notes.ui.theme.SettingsItemsTitleTextStyle
import com.lux.notes.ui.theme.SettingsSectionsTitleTextStyle

@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val checkedDarkTheme by viewModel.isDarkTheme
    val checkedListView by viewModel.isListView

    Scaffold(
        topBar = {
            TopBar(
                title = "Settings",
                isBackIcon = true,
                onBackIconClick = { navController.navigateUp() }
            )
        },
    ) { paddingValues ->
        var themeMode by LocalThemeMode.current
        var uiMode by LocalUiMode.current

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            SettingsSection(
                title = "Main",
                modifier = Modifier.padding(20.dp, 5.dp, 20.dp, 5.dp),
                textStyle = SettingsSectionsTitleTextStyle
            ) {
                SettingsSwitchItem(
                    title = "Dark theme",
                    checked = checkedDarkTheme,
                    onCheckedChange = {
                        themeMode = themeMode.toggle()
                        viewModel.onDarkThemeChanged(it)
                    },
                    textStyle = SettingsItemsTitleTextStyle.copy(
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                )
                SettingsSwitchItem(
                    title = "List view",
                    checked = checkedListView,
                    onCheckedChange = {
                        uiMode = uiMode.toggle()
                        viewModel.onListViewChanged(it)
                    },
                    textStyle = SettingsItemsTitleTextStyle.copy(
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                )
            }
        }
    }


}
