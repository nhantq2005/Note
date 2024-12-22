package com.example.note.feature_note.data.repository

import com.example.note.feature_note.data.data_source.NoteDao
import com.example.note.feature_note.domain.model.Note
import com.example.note.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val dao: NoteDao
):NoteRepository {
    override fun getNotes(): Flow<List<Note>> {
        return dao.getNote()
    }

    override fun getNotesByTitle(title: String): Flow<List<Note>> {
        return dao.getNotesByTitle(title)
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)
    }

    override suspend fun addNote(note: Note) {
        return dao.addNote(note)
    }

    override suspend fun updateNote(note: Note) {
        return dao.updateNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        return dao.deleteNote(note)
    }
}