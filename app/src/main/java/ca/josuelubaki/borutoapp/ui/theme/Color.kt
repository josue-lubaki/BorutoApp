package ca.josuelubaki.borutoapp.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val LightGray = Color(0xFFD8D8D8)
val DarkGray = Color(0xFF2A2A2A)

@get:Composable
val Colors.welcomeScreenBackgroundColor : Color
    get() = if (isLight) LightGray else DarkGray

@get:Composable
val Colors.titleColor : Color
    get() = if (isLight) DarkGray else LightGray

@get:Composable
val Colors.descriptionColor : Color
    get() = if (isLight) DarkGray.copy(alpha = 0.5f) else LightGray.copy(alpha = 0.5f)