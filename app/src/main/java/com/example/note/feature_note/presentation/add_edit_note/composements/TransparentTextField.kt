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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.note.ui.theme.AppTheme
import com.example.note.ui.theme.NoteTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransparentTextField(
    text: String,
    hint: String,
    onChangeValue: (String) -> Unit,
    isSingleLine: Boolean = false,
    onFocusChange: (FocusState) -> Unit,
    textStyle: TextStyle,
    modifier: Modifier =Modifier
) {
    TextField(
        value = text,
        onValueChange = onChangeValue,
        textStyle = textStyle,
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged {
                onFocusChange(it)
            },
        label = { Text(text = hint, fontSize = 16.sp) },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        singleLine = isSingleLine,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            textColor = Color.Black,
            focusedLabelColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )

    )
}

@Preview
@Composable
fun PreviewTextField() {
    NoteTheme {

    }
}