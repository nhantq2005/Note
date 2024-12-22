package com.example.note.feature_note.presentation.notes.composements


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.note.feature_note.domain.model.Note
import com.example.note.feature_note.presentation.notes.NoteEvent
import com.example.note.feature_note.presentation.notes.NoteViewModel
import com.example.note.ui.theme.AppTheme
import com.example.note.ui.theme.NoteTheme
import com.example.note.ui.theme.coralRed
import kotlinx.coroutines.launch

@Composable
fun NoteItem(
    note: Note,
    viewModel: NoteViewModel,
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState
) {
    val scope = rememberCoroutineScope()
    Card(
        modifier = modifier
            .shadow(8.dp, RoundedCornerShape(13.dp))
            .fillMaxWidth()
            .height(250.dp)
            .clip(RoundedCornerShape(5.dp)),
        colors = CardDefaults.cardColors(Color(note.color))
    ) {
        Row() {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.9f)
                    .padding(15.dp)
            ) {
                Text(
                    text = note.title,
                    style = AppTheme.appTypograhy.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    text = note.content,
                    style = AppTheme.appTypograhy.content,
                    maxLines = 6,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                PinButton(isPinned = note.isPin) {
                    viewModel.onEvent(NoteEvent.PinnedNote(note))
                }
                //Show snackbar when delete note
                IconButton(onClick = {
                    viewModel.onEvent(NoteEvent.DeleteNote(note))
                    scope.launch {
                        val result = snackbarHostState.showSnackbar(
                            message = "Bạn có muốn hoàn tác?",
                            actionLabel = "Hoàn tác",
                            duration = SnackbarDuration.Short
                        )
                        if (result == SnackbarResult.ActionPerformed) {
                            viewModel.onEvent(NoteEvent.RestoreNote)
                        }
                    }
                }) {
                    Icon(Icons.Default.Delete, contentDescription = "delete note button")
                }
            }
        }
    }
}

@Preview(showBackground = false, showSystemUi = false, name = "Note Item")
@Composable
fun ReviewNoteItem() {
    NoteTheme {
//        NoteItem()
    }
}