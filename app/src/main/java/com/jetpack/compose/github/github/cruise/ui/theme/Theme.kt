package com.jetpack.compose.github.github.cruise.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    // The background color of the UI.
    background = White,
    // The text color on the background, ensuring readability.
    onBackground = TextDark,
    // secondary text
    onSurface = TextLight,
    // The surface color, used for elements like cards and dialogs.
    surface = CardDialog,
    // The color for primary containers, such as cards or panels.
    primaryContainer = Container,
    // small icons, clear button etc.
    surfaceTint = SmallICons
)


private val LightColorScheme = lightColorScheme(
    background = White,
    onBackground = TextDark,
    onSurface = TextLight,
    surface = CardDialog,
    primaryContainer = Container,
    surfaceTint = SmallICons
)

@Composable
fun GithubCruiseTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}