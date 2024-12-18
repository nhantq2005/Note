package com.example.note.feature_note.presentation.add_edit_note.composements


import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.example.note.feature_note.domain.model.Note
import com.example.note.feature_note.presentation.add_edit_note.AddEditNoteEvent
import com.example.note.feature_note.presentation.add_edit_note.AddEditViewModel
import com.example.note.ui.theme.mintGreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ColorButtons(
        noteColor: Int,
        scope: CoroutineScope,
        viewModel: AddEditViewModel
) {
    val noteBackGroundAnimatable = remember {
        Animatable(
                Color(if (noteColor != -1) noteColor else viewModel.noteColor.value)
        )
    }
    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(Note.noteColors) { color ->
            val colorInt = color.toArgb()
            Box(modifier = Modifier
                    .size(50.dp)
                    .shadow(15.dp, CircleShape)
                    .background(color)
                    .border(
                            width = 3.dp,
                            color = if (viewModel.noteColor.value == colorInt) {
                                Color.Black
                            } else {
                                Color.Transparent
                            },
                            shape = CircleShape
                    )
                    .clickable {
                        scope.launch {
                            noteBackGroundAnimatable.animateTo(
                                    targetValue = Color(colorInt),
                                    animationSpec = tween(
                                            durationMillis = 500
                                    )
                            )
                        }
                        viewModel.onEvent(AddEditNoteEvent.ChangeColor(colorInt))
                    }
            )
            Spacer(modifier = Modifier.width(10.dp))
        }
    }

}