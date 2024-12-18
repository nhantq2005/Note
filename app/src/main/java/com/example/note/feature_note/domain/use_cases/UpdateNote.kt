package com.example.note.feature_note.domain.use_cases

import com.example.note.feature_note.domain.model.Note
import com.example.note.feature_note.domain.repository.NoteRepository

class UpdateNote(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(note: Note){
        noteRepository.updateNote(note.copy(isPin = !note.isPin))
    }
}