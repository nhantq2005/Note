package com.example.note.feature_note.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.note.feature_note.presentation.add_edit_note.AddEditNoteScreen
import com.example.note.feature_note.presentation.notes.HomeScreen
import com.example.note.ui.theme.AppTheme
import com.example.note.ui.theme.NoteTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val navController = rememberNavController()
                NavHost(
                        navController = navController,
                        startDestination = Screen.HomeScreen.route){
                    composable(Screen.HomeScreen.route){
                        HomeScreen(navController = navController)
                    }
                    composable(Screen.AddEditScreen.route +
                    "?noteId={noteId}&noteColor={noteColor}",
                            arguments = listOf(
                                    navArgument(
                                            name = "noteId"
                                    ){
                                        type = NavType.IntType
                                        defaultValue = -1
                                    },
                                    navArgument(
                                            name = "noteColor"
                                    ){
                                        type = NavType.IntType
                                        defaultValue = -1
                                    }
                            )){
                        val color = it.arguments?.getInt("noteColor") ?: -1
                        AddEditNoteScreen(navController = navController, noteColor = color)
                    }
                }

            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NoteTheme {

    }
}