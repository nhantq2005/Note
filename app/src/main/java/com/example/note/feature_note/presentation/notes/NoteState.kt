package com.example.note.feature_note.presentation.notes


import com.example.note.feature_note.domain.model.Note
import com.example.note.feature_note.domain.util.NoteOrder

data class NoteState(
    val notes: List<Note> = emptyList(),
    val searchNotes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(),
    val keyword: String = ""
)