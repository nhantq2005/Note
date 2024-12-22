package com.example.note.feature_note.domain.use_cases

import com.example.note.feature_note.domain.model.Note
import com.example.note.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import java.util.Collections

class FindNote(
        private val noteRepository: NoteRepository
) {
    operator fun invoke(title: String): Flow<List<Note>> {
        return noteRepository.getNotesByTitle(title)
    }
}