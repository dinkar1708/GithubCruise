package com.jetpack.compose.github.github.cruise.ui.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
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
    Box(modifier.padding(all = 8.dp)) {
        when {
            isLoading -> {
                SharedProgressIndicator()
            }

            errorMessage.isNotEmpty() -> {
                SharedErrorView(errorMessage = errorMessage)
            }

            else -> {
                content()
            }
        }
    }
}
