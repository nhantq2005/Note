package com.example.note.feature_note.presentation.add_edit_note.composements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.example.note.ui.theme.NoteTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransparentTextField(
        text: String,
        hint: String,
        onChangeValue: (String) -> Unit,
        isSingleLine: Boolean = false,
        onFocusChange: (FocusState) -> Unit,
        modifier: Modifier = Modifier
) {
    TextField(
            value = text,
            onValueChange = onChangeValue,
            modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged {
                        onFocusChange(it)
                    },
            label = { Text(text = hint) },
            keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
            ),
            singleLine = isSingleLine,
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent,
                    textColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent)
    )
}

@Preview
@Composable
fun PreviewTextField() {
    NoteTheme {
        var textvalue by remember {
            mutableStateOf("")
        }
//        TransparentTextField(text = textvalue, onChangeValue = {textvalue = it}, onFocusChange = {})
    }
}