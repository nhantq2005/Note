package com.example.note.feature_note.presentation

sealed class Screen(val route:String) {
    object HomeScreen:Screen("home_screen")
    object AddEditScreen:Screen("add_edit_screen")
}