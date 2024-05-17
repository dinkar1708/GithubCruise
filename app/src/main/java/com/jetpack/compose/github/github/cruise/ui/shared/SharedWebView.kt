package com.jetpack.compose.github.github.cruise.ui.shared

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.jetpack.compose.github.github.cruise.ui.theme.GithubCruiseTheme

/**
 * Created by Dinakar Maurya on 2024/05/15.
 */
@Composable
fun SharedWebView(url: String) {
    val isLoading = remember { mutableStateOf(true) }

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                WebView(context).apply {
                    webViewClient = object : WebViewClient() {
                        override fun onPageFinished(view: WebView?, url: String?) {
                            super.onPageFinished(view, url)
                            isLoading.value = false
                        }
                    }
                    loadUrl(url)
                }
            }
        )
        if (isLoading.value) {
            SharedProgressIndicator()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SharedWebViewPreview() {
    GithubCruiseTheme {
        Surface {
            SharedWebView(url = "Error")
        }
    }
}
