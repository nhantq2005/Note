package com.example.note.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class AppColor(
    val searchItemColor: Color = Color.Unspecified,
    val searchTextFieldColor: Color = Color.Unspecified,
    val fabColor: Color = Color.Unspecified,
    val contentFAB: Color = Color.Unspecified,
    val bottomBarColor: Color = Color.Unspecified,
    val chipColor: Color = Color.Unspecified
)

val LocalAppColor = staticCompositionLocalOf {
    AppColor()
}