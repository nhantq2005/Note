package com.example.note.feature_note.domain.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.note.ui.theme.brownClay
import com.example.note.ui.theme.coralRed
import com.example.note.ui.theme.darkBlue
import com.example.note.ui.theme.greenGray
import com.example.note.ui.theme.mintGreen
import com.example.note.ui.theme.pastelGray
import com.example.note.ui.theme.peachOrange
import com.example.note.ui.theme.purpleSunset
import com.example.note.ui.theme.redPeach
import com.example.note.ui.theme.sandBrown
import com.example.note.ui.theme.smokyGray
import com.example.note.ui.theme.white

@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id:Int? = 0,
    val title:String,
    val content:String,
    val color:Int,
    val isPin:Boolean,
    val timeStamp: Long
)

{
    companion object {
        val noteColors = listOf(
            white, coralRed, peachOrange, darkBlue, sandBrown, mintGreen, greenGray,
            pastelGray, smokyGray, purpleSunset, redPeach, brownClay
        )
    }
}

class InvalidNoteException(message:String):Exception(message){

}