package com.example.note.feature_note.domain.use_cases

data class NoteUseCases (
    val getNote: GetNote,
    val getNotes: GetNotes,
    val addNote: AddNote,
    val deleteNote: DeleteNote,
    val updateNote: UpdateNote,
//    val findNote: FindNote
)