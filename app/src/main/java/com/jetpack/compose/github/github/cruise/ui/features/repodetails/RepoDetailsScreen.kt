package com.jetpack.compose.github.github.cruise.ui.features.repodetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jetpack.compose.github.github.cruise.ui.shared.AppActionBarView
import com.jetpack.compose.github.github.cruise.ui.shared.SharedWebView
import com.jetpack.compose.github.github.cruise.ui.theme.GithubCruiseTheme

/**
 * Created by Dinakar Maurya on 2024/05/15.
 */
@Composable
fun RepoDetailsScreen(
    htmlUrl: String, onBackClick: () -> Unit
) {
    Column(
        modifier =
        Modifier
            .background(MaterialTheme.colorScheme.background)
    ) {
        AppActionBarView(
            modifier = Modifier
                .fillMaxWidth(),
            headerText = "Repository Details",
            showBackButton = true,
            onBackClick = onBackClick
        )

        SharedWebView(
            url = htmlUrl
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenCommonHeaderPreview() {
    GithubCruiseTheme {
        Surface {
            SharedWebView("")
        }
    }
}
