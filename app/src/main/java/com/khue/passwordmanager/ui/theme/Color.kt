package com.khue.passwordmanager.ui.theme


import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val AppColor = Color(0xFF3F7DE3)
val TopHeadingColor = Color(0xFFCCCCCC)
val Red = Color(0xFFF04646)


val ColorScheme.appColor
    @Composable
    get() = AppColor

val ColorScheme.topHeadingColor
    @Composable
    get() = TopHeadingColor

val ColorScheme.red
    @Composable
    get() = Red