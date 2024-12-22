package com.example.note.feature_note.presentation.add_edit_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note.feature_note.domain.model.InvalidNoteException
import com.example.note.feature_note.domain.model.Note
import com.example.note.feature_note.domain.use_cases.NoteUseCases
import com.example.note.feature_note.presentation.notes.NoteEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _noteTitle = mutableStateOf(
        AddEditNoteState(
            hint = "Tiêu đề"
        )
    )
    val noteTitle: State<AddEditNoteState> = _noteTitle

    private val _noteContent = mutableStateOf(
        AddEditNoteState(
            hint = "Nội dung"
        )
    )

    private val _state = mutableStateOf(AddEditNoteState())
    val state: State<AddEditNoteState> = _state

    val noteContent: State<AddEditNoteState> = _noteContent

    private val _noteColor = mutableStateOf<Int>(Note.noteColors.random().toArgb())
    val noteColor: State<Int> = _noteColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentNoteId: Int? = null

    //Show Content and Title of note
    //get note by id
    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if (noteId != -1) {
                viewModelScope.launch {
                    noteUseCases.getNote(noteId)?.also { note ->
                        currentNoteId = note.id
                        _noteTitle.value = noteTitle.value.copy(
                            text = note.title
                        )
                        _noteContent.value = noteContent.value.copy(
                            text = note.content
                        )
                        _state.value = state.value.copy(
                            isPinned = note.isPin
                        )
                        _noteColor.value = note.color
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditNoteEvent) {
        when (event) {
            is AddEditNoteEvent.ChangeColor -> {
                _noteColor.value = event.color
            }

            is AddEditNoteEvent.ChangeContentFocus -> {
                _noteContent.value = noteContent.value.copy(
                    isHintVisible = !event.focusState.isFocused
                            && noteContent.value.text.isBlank()
                )
            }

            is AddEditNoteEvent.ChangeTitleFocus -> {
                _noteTitle.value = noteTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused
                            && noteTitle.value.text.isBlank()
                )
            }

            is AddEditNoteEvent.EnteredContent -> {
                _noteContent.value = noteContent.value.copy(
                    text = event.content
                )
            }

            is AddEditNoteEvent.EnteredTitle -> {
                _noteTitle.value = noteTitle.value.copy(
                    text = event.title
                )
            }

            AddEditNoteEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        noteUseCases.addNote(
                            Note(
                                title = noteTitle.value.text,
                                content = noteContent.value.text,
                                timeStamp = System.currentTimeMillis(),
                                color = noteColor.value,
                                isPin = state.value.isPinned,
                                id = currentNoteId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveTask)
                    } catch (e: InvalidNoteException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = e.message ?: "Unknow"
                            )
                        )
                    }
                }
            }

            is AddEditNoteEvent.PinnedNote -> {
                _state.value = state.value.copy(
                    isPinned = !state.value.isPinned
                )
            }

            AddEditNoteEvent.ToggleColorSection -> {
                _state.value = state.value.copy(
                    isColorButtonsVisible = !state.value.isColorButtonsVisible
                )
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        object SaveTask : UiEvent()
    }
}

