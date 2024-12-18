package com.example.note.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle


data class AppTypography(
    val title: TextStyle = TextStyle.Default,
    val content: TextStyle = TextStyle.Default,
    val placeHolder: TextStyle = TextStyle.Default,
    val contentFAB: TextStyle = TextStyle.Default
)

val LocalAppTypography = staticCompositionLocalOf {
    AppTypography()
}