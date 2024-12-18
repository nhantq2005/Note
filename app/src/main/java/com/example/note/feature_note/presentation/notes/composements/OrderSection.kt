package com.example.note.feature_note.presentation.notes.composements

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.SortByAlpha
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.note.feature_note.domain.util.NoteOrder
import com.example.note.ui.theme.AppTheme
import com.example.note.ui.theme.NoteTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    noteOrder: NoteOrder = NoteOrder.Date(),
    onOrderChange: (NoteOrder) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        FilterChip(
            onClick = { onOrderChange(NoteOrder.Title()) },
            label = {
                Icon(Icons.Default.SortByAlpha, contentDescription = "AtoZ")
                Spacer(modifier = Modifier.width(5.dp))
                Text("Tiêu đề")
            },
            selected = noteOrder is NoteOrder.Title,
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = AppTheme.appColor.chipColor
            )
        )
        Spacer(modifier = Modifier.width(15.dp))
        FilterChip(
            onClick = { onOrderChange(NoteOrder.Date()) },
            label = {
                Icon(Icons.Default.CalendarMonth, contentDescription = "Calender")
                Spacer(modifier = Modifier.width(5.dp))
                Text("Ngày")
            },
            selected = noteOrder is NoteOrder.Date,
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = AppTheme.appColor.chipColor
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ReviewOrderSection() {
    NoteTheme {
//        OrderSection()
    }
}
