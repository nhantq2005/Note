package com.example.note.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
//import com.example.note.Note
import com.example.note.feature_note.domain.model.Note
import com.example.note.feature_note.domain.use_cases.NoteUseCases
import com.example.note.feature_note.domain.util.NoteOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {
    private val _state = mutableStateOf(NoteState())
    val state: State<NoteState> = _state

    private val _searchKeyword = mutableStateOf(NoteState())
    val searchKeyword: State<NoteState> = _searchKeyword

    private var recentNote: Note? = null

    private var getNotesJob: Job? = null

    init {
        getNotes(NoteOrder.Date())
    }

    fun onEvent(event: NoteEvent) {
        when (event) {
            is NoteEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNote(event.note)
                    recentNote = event.note
                }
            }

            is NoteEvent.Order -> {
                if (state.value.noteOrder::class == event.noteOrder::class) {
                    return
                }
                getNotes(event.noteOrder)
            }

            is NoteEvent.PinnedNote -> {
                viewModelScope.launch {
                    noteUseCases.updateNote(event.note)
                }
            }

            NoteEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCases.addNote(recentNote ?: return@launch)
                    recentNote = null
                }
            }

            is NoteEvent.FindNote -> {
                getNotesJob?.cancel()
                //Cancel previous job get note
                getNotesJob = noteUseCases.findNote(searchKeyword.value.keyword)
                    .onEach { notes ->
                        _state.value = state.value.copy(
                            searchNotes = notes,
                            noteOrder = NoteOrder.Date()
                        )
                    }.launchIn(viewModelScope)
            }

            is NoteEvent.EnteredSearch -> {
                _searchKeyword.value = searchKeyword.value.copy(
                    keyword = event.keyword
                )
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder) {
        //Cancel previous job get note
        getNotesJob?.cancel()
        getNotesJob = noteUseCases.getNotes(noteOrder)
            .onEach { notes ->
                _state.value = state.value.copy(
                    notes = notes,
                    noteOrder = noteOrder
                )
            }.launchIn(viewModelScope)
    }


}