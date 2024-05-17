package com.jetpack.compose.github.github.cruise.ui.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpack.compose.github.github.cruise.ui.theme.GithubCruiseTheme

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
    Box(modifier.padding(all = 8.dp),  contentAlignment = Alignment.TopStart ) {
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

@Preview(showBackground = true)
@Composable
fun StateContentBoxPreview() {
    GithubCruiseTheme {
        Surface {
            StateContentBox(
                isLoading = false,
                errorMessage = "Error"
            ) {
                Text(text = "hello")
            }
        }
    }
}
