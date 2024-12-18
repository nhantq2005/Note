package com.example.note.feature_note.presentation.notes

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ad_coding.noteappcourse.R
import com.example.note.feature_note.presentation.Screen
import com.example.note.feature_note.presentation.notes.composements.NoteItem
import com.example.note.feature_note.presentation.notes.composements.OrderSection
import com.example.note.ui.theme.AppTheme
import com.example.note.ui.theme.NoteTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: NoteViewModel = hiltViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val state = viewModel.state.value
    val searchKeyword = viewModel.searchKeyword.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(10.dp))
        OrderSection(
            onOrderChange = {
                viewModel.onEvent(NoteEvent.Order(it))
            },
            noteOrder = state.noteOrder
        )
        Spacer(modifier = Modifier.height(10.dp))
        if (state.notes.isEmpty()) {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painterResource(id = R.drawable.empty),
                    contentDescription = "Empty",
                    modifier = Modifier.size(80.dp)
                )
                Text(
                    text = "Không có ghi chú",

                    )
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.notes) { note ->
                    NoteItem(
                        note = note,
                        viewModel = viewModel,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(
                                    Screen.AddEditScreen.route
                                            + "?noteId=${note.id}&noteColor=${note.color}"
                                )
                            },
                        snackbarHostState = snackbarHostState
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        FloatingActionButton(
            onClick = {
                navController.navigate(Screen.AddEditScreen.route)
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp),
            containerColor = AppTheme.appColor.fabColor
        ){
            Icon(
                Icons.Default.Edit,
                contentDescription = "Add note button",
                tint = AppTheme.appColor.contentFAB
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReviewHomeScreen() {
    NoteTheme(darkTheme = false) {
//        HomeScreen()
    }
}