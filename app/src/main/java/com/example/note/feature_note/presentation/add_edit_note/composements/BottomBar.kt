package com.example.note.feature_note.presentation.add_edit_note.composements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.InspectableModifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.note.feature_note.presentation.notes.composements.PinButton
import com.example.note.ui.theme.NoteTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBar(
//    content:@Composable ()->Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Thêm ghi chú")
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back button")
                    }
                },
                actions = {
                    Button(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.Save, contentDescription = "Save button")
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = "Lưu")
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
            ) {
                IconButton(
                    onClick = { /*TODO*/ },
                ) {
                    Icon(Icons.Outlined.Palette, contentDescription = "Change Note Color Button")
                }
                PinButton(isPinned = false) {

                }


            }

        },

        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBottomBar() {
    NoteTheme {
        BottomBar()
    }
}