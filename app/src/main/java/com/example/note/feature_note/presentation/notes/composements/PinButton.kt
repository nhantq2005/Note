package com.example.note.feature_note.presentation.notes.composements

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PushPin
import androidx.compose.material.icons.outlined.PushPin
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.ad_coding.noteappcourse.R
import com.example.note.ui.theme.NoteTheme

@Composable
fun PinButton(
    isPinned:Boolean,
    onChanged:()->Unit
){
    IconButton(onClick = onChanged) {
        if(isPinned){
            Icon(Icons.Filled.PushPin, contentDescription = "pinned")
        }else{
            Icon(Icons.Outlined.PushPin, contentDescription = "not_pin")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PinButtonPreview(){
    NoteTheme {
        var demoBool by remember{
            mutableStateOf(false)
        }
        PinButton(isPinned = demoBool) {
            demoBool = !demoBool
        }
    }
}