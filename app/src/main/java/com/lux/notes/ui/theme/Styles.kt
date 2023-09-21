package com.lux.notes.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.lux.notes.R

val NoteTitleTextStyle = TextStyle(
    fontSize = 22.sp,
    lineHeight = 20.sp,
    fontFamily = FontFamily(Font(R.font.roboto)),
    fontWeight = FontWeight(600)
)

val NoteTextStyle = TextStyle(
    fontSize = 16.sp,
    lineHeight = 30.sp,
    fontFamily = FontFamily(Font(R.font.roboto)),
    fontWeight = FontWeight(400),
    letterSpacing = 0.75.sp,
)

val SubtitleTextStyle = TextStyle(
    fontSize = 12.sp,
    lineHeight = 16.sp,
    fontFamily = FontFamily(Font(R.font.roboto)),
    fontWeight = FontWeight(400),
    color = SubtitleColor
)

val SettingsItemsTitleTextStyle = TextStyle(
    fontSize = 18.sp,
    fontFamily = FontFamily(Font(R.font.roboto)),
    fontWeight = FontWeight(550)
)

val SettingsSectionsTitleTextStyle = TextStyle(
    fontSize = 20.sp,
    fontFamily = FontFamily(Font(R.font.roboto)),
    fontWeight = FontWeight(550),
    color = SubtitleColor
)