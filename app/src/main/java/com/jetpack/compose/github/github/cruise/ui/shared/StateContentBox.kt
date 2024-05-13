package com.jetpack.compose.github.github.cruise.ui.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Created by Dinakar Maurya on 2024/05/13.
 */
@Composable
fun StateContentBox(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    errorMessage: String,
    content: @Composable () -> Unit
) {
    Box(modifier) {
        when {
            isLoading -> {
                SharedProgressIndicator()
            }

            errorMessage.isNotEmpty() -> {
                Text(
                    text = errorMessage,
                    style = MaterialTheme.typography.bodyLarge.copy(MaterialTheme.colorScheme.onBackground),
                    modifier = modifier
                        .fillMaxSize()
                        .padding(top = 64.dp)
                        .wrapContentSize(Alignment.TopCenter)
                )
            }

            else -> {
                content()
            }
        }
    }
}
