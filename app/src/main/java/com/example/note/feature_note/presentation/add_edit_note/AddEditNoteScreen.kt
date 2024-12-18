package com.example.note.feature_note.presentation.add_edit_note


import androidx.compose.animation.Animatable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.note.feature_note.domain.model.Note
import com.example.note.feature_note.presentation.Screen
import com.example.note.feature_note.presentation.add_edit_note.composements.ColorButtons
import com.example.note.feature_note.presentation.add_edit_note.composements.TransparentTextField
import com.example.note.feature_note.presentation.notes.NoteEvent
import com.example.note.feature_note.presentation.notes.composements.PinButton
import com.example.note.ui.theme.AppTheme
import com.example.note.ui.theme.mintGreen
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditNoteScreen(
    navController: NavController,
    noteColor: Int,
    viewModel: AddEditViewModel = hiltViewModel()
) {
    val titleState = viewModel.noteTitle.value
    val contentState = viewModel.noteContent.value
    val scope = rememberCoroutineScope()
    val state = viewModel.state.value
    val pinState = viewModel.state.value
    val snackbarHostState = remember { SnackbarHostState() }
    val noteBackGroundAnimatable = remember {
        Animatable(
            Color(if (noteColor != -1) noteColor else viewModel.noteColor.value)
        )
    }
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddEditViewModel.UiEvent.ShowSnackBar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                    navController.navigateUp()
                }
                AddEditViewModel.UiEvent.SaveTask -> {
                    navController.navigateUp()
                }
            }
        }
    }
    Scaffold(
        bottomBar = {
            BottomAppBar(
                Modifier.clip(
                    RoundedCornerShape(topEndPercent = 15, topStartPercent = 15),
                ),
                containerColor = AppTheme.appColor.bottomBarColor
            ) {
                IconButton(onClick = {
                    navController.navigate(Screen.HomeScreen.route)
                }) {
                    Icon(Icons.Outlined.Home, contentDescription = "Back Home Button")
                }
                PinButton(isPinned = pinState.isPinned) {
                    viewModel.onEvent(AddEditNoteEvent.PinnedNote(pinState.isPinned))
                }
                IconButton(onClick = {
                    viewModel.onEvent(AddEditNoteEvent.ToggleColorSection)
                }) {
                    Icon(Icons.Outlined.Palette, contentDescription = "Palette Color")
                }
                IconButton(onClick = {
//                        viewModel.onEvent(AddEditNoteEvent.DeleteNote(note))
                }) {
                    Icon(Icons.Outlined.Delete, contentDescription = "Delete Button")
                }
            }

        }, containerColor = noteBackGroundAnimatable.value
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                TransparentTextField(
                    text = titleState.text,
                    hint = titleState.hint,
                    onChangeValue = {
                        viewModel.onEvent(AddEditNoteEvent.EnteredTitle(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(AddEditNoteEvent.ChangeTitleFocus(it))
                    },
                    isSingleLine = true,
                    textStyle = AppTheme.appTypograhy.title
                )
                Spacer(modifier = Modifier.height(10.dp))
                TransparentTextField(
                    text = contentState.text,
                    hint = contentState.hint,
                    onChangeValue = {
                        viewModel.onEvent(AddEditNoteEvent.EnteredContent(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(AddEditNoteEvent.ChangeContentFocus(it))
                    },
                    textStyle = AppTheme.appTypograhy.content
                )

            }
            AnimatedVisibility(
                visible = state.isColorButtonsVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically(),
                modifier = Modifier.align(Alignment.BottomCenter)
                    .padding(5.dp)
            ) {
//            ColorButtons(noteColor = noteColor, scope = scope, viewModel = viewModel)

                LazyRow(modifier = Modifier.fillMaxWidth()) {
                    items(Note.noteColors) { color ->
                        val colorInt = color.toArgb()
                        Box(modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
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
                                viewModel.onEvent(AddEditNoteEvent.ToggleColorSection)
                            }
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                }
            }
        }

    }
    Box(modifier = Modifier.fillMaxSize()) {
        FloatingActionButton(
            onClick = {
                viewModel.onEvent(AddEditNoteEvent.SaveNote)
            },
            Modifier
                .align(Alignment.BottomEnd)
                .padding(10.dp),
            containerColor = AppTheme.appColor.fabColor
        ) {
            Icon(
                Icons.Outlined.Check,
                contentDescription = "Save Note",
                tint = AppTheme.appColor.contentFAB
            )
        }


    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewAddEditScreen() {
//    NoteTheme {
//        AddEditNoteScreen()
//    }
//}