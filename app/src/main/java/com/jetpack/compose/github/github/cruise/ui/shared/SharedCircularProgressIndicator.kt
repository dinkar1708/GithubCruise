package com.jetpack.compose.github.github.cruise.ui.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpack.compose.github.github.cruise.ui.theme.GithubCruiseTheme

@Composable
fun SharedProgressIndicator(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = Color.Gray,
            strokeWidth = 5.dp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SharedProgressIndicatorPreview() {
    GithubCruiseTheme {
        Surface {
            SharedProgressIndicator()
        }
    }
}