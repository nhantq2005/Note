package com.example.note.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun AppTheme(
    content:@Composable ()->Unit
){
    val typography = AppTypography(
        title = TextStyle(
            fontFamily = OpenSansFamily,
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold
        ),
        content = TextStyle(
            fontFamily = OpenSansFamily,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        ),
        contentFAB = TextStyle(
            fontFamily = OpenSansFamily,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        ),
        placeHolder = TextStyle(
            fontFamily = OpenSansFamily,
            fontSize = 17.sp,
            fontWeight = FontWeight.Medium
        )
    )

    val color = AppColor(
        bottomBarColor = Color(0xFFF7ECDF),
        fabColor = Color(0xFFF2DDB8),
        searchTextFieldColor = Color(0xFFF1E7D9),
        searchItemColor = Color(0xFF4D4639),
        contentFAB = Color(0xFF52452A),
        chipColor = Color(0xFFF2DDB8),
        actionLabel = Color(0xFFE9C16C)
    )

    CompositionLocalProvider(
        LocalAppColor provides color,
        LocalAppTypography provides typography
    ) {
        content.invoke()
    }
}

object AppTheme{
    val appTypograhy:AppTypography
        @Composable
        get() = LocalAppTypography.current

    val appColor:AppColor
        @Composable
        get() = LocalAppColor.current
}