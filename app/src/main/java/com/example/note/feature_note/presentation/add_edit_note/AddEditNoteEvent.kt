package com.example.note.feature_note.presentation.add_edit_note

import androidx.compose.ui.focus.FocusState
import com.example.note.feature_note.domain.model.Note
import com.example.note.feature_note.presentation.notes.NoteEvent

sealed class AddEditNoteEvent {

    data class EnteredTitle(val title:String):AddEditNoteEvent()
    data class ChangeTitleFocus(val focusState: FocusState):AddEditNoteEvent()
    data class EnteredContent(val content:String):AddEditNoteEvent()
    data class ChangeContentFocus(val focusState: FocusState):AddEditNoteEvent()
    data class ChangeColor(val color:Int):AddEditNoteEvent()
    data class PinnedNote(val isPin: Boolean): AddEditNoteEvent()
    object SaveNote:AddEditNoteEvent()
    object ToggleColorSection:AddEditNoteEvent()
}