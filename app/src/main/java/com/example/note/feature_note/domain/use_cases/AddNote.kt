package com.example.note.feature_note.domain.use_cases

import com.example.note.feature_note.domain.model.InvalidNoteException
import com.example.note.feature_note.domain.model.Note
import com.example.note.feature_note.domain.repository.NoteRepository

class AddNote(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(note: Note){
        if(note.title.isBlank()){
            throw InvalidNoteException("The title can't be empty")
        }
        return noteRepository.addNote(note)
    }
}