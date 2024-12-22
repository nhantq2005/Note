package com.example.note.feature_note.presentation.notes

import com.example.note.feature_note.domain.model.Note
import com.example.note.feature_note.domain.util.NoteOrder

sealed class NoteEvent {
    data class DeleteNote(val note: Note):NoteEvent()

    data class Order(val noteOrder: NoteOrder):NoteEvent()

    data class PinnedNote(val note: Note):NoteEvent()

    data class FindNote(val noteTitle:String):NoteEvent()

    data class EnteredSearch(val keyword:String):NoteEvent()

    object RestoreNote:NoteEvent()
}