package com.example.note.feature_note.presentation.notes.composements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.note.feature_note.presentation.notes.NoteState
import com.example.note.feature_note.presentation.notes.NoteViewModel
import com.example.note.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField(
    searchKeyword: NoteState,
    viewModel: NoteViewModel
) {
    TextField(
        value = searchKeyword.keyword,
        onValueChange = {
//                                viewModel.onEvent(NoteEvent.EnteredSearch(it))
//                                if(searchKeyword.keyword.isNotEmpty()) viewModel.onEvent(NoteEvent.FindNote(it))
        },
        placeholder = {
            Text(
                text = "Tìm kiếm",
                style = AppTheme.appTypograhy.placeHolder,
                color = AppTheme.appColor.searchItemColor
            )
        },
        trailingIcon = {
            IconButton(onClick = {
//                        viewModel.onEvent(NoteEvent.FindNote(demoText))
            }) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Search Button",
                    tint = AppTheme.appColor.searchItemColor
                )
            }
        },
        modifier = Modifier.fillMaxWidth(0.95f),
        shape = RoundedCornerShape(30.dp),
        colors = TextFieldDefaults.textFieldColors(AppTheme.appColor.searchTextFieldColor)
    )
}