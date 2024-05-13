package com.jetpack.compose.github.github.cruise.ui.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

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
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                )
            }

            else -> {
                content()
            }
        }
    }
}
