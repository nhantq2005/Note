package com.example.note.di

import android.app.Application
import androidx.room.Room
import com.example.note.feature_note.data.data_source.NoteDatabase
import com.example.note.feature_note.data.repository.NoteRepositoryImpl
import com.example.note.feature_note.domain.repository.NoteRepository
import com.example.note.feature_note.domain.use_cases.AddNote
import com.example.note.feature_note.domain.use_cases.DeleteNote
//import com.example.note.feature_note.domain.use_cases.FindNote
import com.example.note.feature_note.domain.use_cases.GetNote
import com.example.note.feature_note.domain.use_cases.GetNotes
import com.example.note.feature_note.domain.use_cases.NoteUseCases
import com.example.note.feature_note.domain.use_cases.UpdateNote
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNoteDatabase(app:Application):NoteDatabase{
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db:NoteDatabase):NoteRepository{
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository):NoteUseCases{
        return NoteUseCases(
            getNotes = GetNotes(repository),
            getNote = GetNote(repository),
            addNote = AddNote(repository),
            deleteNote = DeleteNote(repository),
            updateNote = UpdateNote(repository)
//            findNote = FindNote(repository)
        )
    }
}