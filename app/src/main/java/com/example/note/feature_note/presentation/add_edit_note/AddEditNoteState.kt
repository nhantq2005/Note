package com.example.note.feature_note.presentation.add_edit_note

data class AddEditNoteState(
        val text: String = "",
        val hint: String = "",
        val isHintVisible: Boolean = false,
        val isPinned: Boolean = false,
        val isColorButtonsVisible: Boolean = false
)