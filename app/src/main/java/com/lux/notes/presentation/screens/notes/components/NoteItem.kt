package com.lux.notes.presentation.screens.notes.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lux.notes.R
import com.lux.notes.domain.models.Note
import com.lux.notes.presentation.utils.toLocalDateTime
import java.time.format.DateTimeFormatter

data class NoteItemColors(
    val containerColor: Color,
    val titleColor: Color,
    val contentColor: Color,
    val dateColor: Color
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteItem(
    note: Note,
    modifier: Modifier = Modifier,
    noteTextMaxLine: Int = 3,
    colors: NoteItemColors,
    onClick: () -> Unit = {},
) {

    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = colors.containerColor),
        shape = RoundedCornerShape(size = 12.dp),
        elevation = CardDefaults.cardElevation(3.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start,
        ) {
            // Note Title
            Text(
                text = note.title.ifEmpty { "No title" },
                fontSize = 18.sp,
                lineHeight = 24.sp,
                fontFamily = FontFamily(Font(R.font.roboto)),
                fontWeight = FontWeight(1000),
                letterSpacing = 0.5.sp,
                color = colors.titleColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            // Note Text
            Text(
                text = note.text,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontFamily = FontFamily(Font(R.font.roboto)),
                fontWeight = FontWeight(400),
                color = colors.contentColor,
                letterSpacing = 0.25.sp,
                textAlign = TextAlign.Justify,
                maxLines = noteTextMaxLine,
                overflow = TextOverflow.Ellipsis
            )

            // Note Date Created
            Text(
                text = note.dateCreated.toLocalDateTime()
                    .format(DateTimeFormatter.ofPattern("dd.MM.yyy HH:mm")),
                fontSize = 12.sp,
                lineHeight = 20.sp,
                fontFamily = FontFamily(Font(R.font.roboto)),
                fontWeight = FontWeight(400),
                color = colors.dateColor,
                letterSpacing = 0.25.sp,
            )
        }
    }
}
