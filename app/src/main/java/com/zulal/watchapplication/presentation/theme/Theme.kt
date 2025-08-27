package com.zulal.watchapplication.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.wear.compose.material.Colors
import androidx.wear.compose.material.MaterialTheme

// Accent renkler
val OrangeAccent = Color(0xFFFF6900)
val PurpleAccent = Color(0xFF9810FA)
val GreenAccent = Color(0xFF4CAF50)
val RedAccent = Color(0xFFF44336)
val GrayAccent = Color(0xFFBDBDBD)

val BlackAccent = Color(0xFF000000)


val DarkColorPalette = Colors(
    primary = Color.White,
    primaryVariant = Color.Gray,
    secondary = Color.White,
    secondaryVariant = Color.LightGray,
    background = Color.Black,
    surface = Color.Black,
    error = Color.Red,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White,
    onError = Color.White,
)

@Composable
fun WatchAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = DarkColorPalette,
        content = content
    )
}
