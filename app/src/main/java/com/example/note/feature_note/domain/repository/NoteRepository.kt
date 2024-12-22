package com.example.note.feature_note.domain.repository

import com.example.note.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getNotes():Flow<List<Note>>

    fun getNotesByTitle(title:String):Flow<List<Note>>

    suspend fun getNoteById(id:Int):Note?

    suspend fun addNote(note: Note)

    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note: Note)

}