package com.example.note.feature_note.domain.util

sealed class NoteOrder {
    class Title():NoteOrder()
    class Date():NoteOrder()
}