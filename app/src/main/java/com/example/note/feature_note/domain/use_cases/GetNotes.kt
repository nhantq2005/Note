package com.example.note.feature_note.domain.use_cases

import com.example.note.feature_note.domain.model.Note
import com.example.note.feature_note.domain.repository.NoteRepository
import com.example.note.feature_note.domain.util.NoteOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class GetNotes(
        private val noteRepository: NoteRepository
) {
    operator fun invoke(
            noteOrder: NoteOrder = NoteOrder.Date()
    ): Flow<List<Note>> {
        return noteRepository.getNotes().map { notes ->
            when (noteOrder) {
                is NoteOrder.Date -> {
                    notes.sortedBy { -it.timeStamp  }.sortedBy { !it.isPin }
                }
                is NoteOrder.Title -> {
                    notes.sortedBy { it.title.lowercase() }.sortedBy { !it.isPin }
                }
            }
        }
    }
}