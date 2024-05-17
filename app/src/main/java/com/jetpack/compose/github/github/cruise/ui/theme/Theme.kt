package com.jetpack.compose.github.github.cruise.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable


private val LightColorScheme = lightColorScheme(
    background = White,
    onBackground = BLACK_DARK,

    primary = DarkColor,
    onPrimary = WhiteLight,

    primaryContainer = MediumDarkColor,
    onPrimaryContainer = WhiteLight2,

    onSurface = TextLight,
    surface = CardDialog,
    surfaceTint = SmallICons,
    error = Alert
)

private val DarkColorScheme = darkColorScheme(
    background = BLACK_DARK,
    onBackground = White,

    primary = WhiteLight,
    onPrimary = DarkColor,

    primaryContainer = WhiteLight2,
    onPrimaryContainer = MediumDarkColor,

    // secondary text
    onSurface = TextLight,
    // The surface color, used for elements like cards and dialogs.
    surface = CardDialog,
    // small icons, clear button etc.
    surfaceTint = SmallICons,
    // Error
    error = Alert
)

@Composable
fun GithubCruiseTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
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