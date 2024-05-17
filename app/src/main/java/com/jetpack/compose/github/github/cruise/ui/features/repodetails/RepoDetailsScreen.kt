package com.jetpack.compose.github.github.cruise.ui.features.repodetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.jetpack.compose.github.github.cruise.R
import com.jetpack.compose.github.github.cruise.ui.shared.AppActionBarView
import com.jetpack.compose.github.github.cruise.ui.shared.SharedWebView
import com.jetpack.compose.github.github.cruise.ui.theme.GithubCruiseTheme

/**
 * Created by Dinakar Maurya on 2024/05/15.
 */
@Composable
fun RepoDetailsScreen(
    navController: NavHostController,
    htmlUrl: String
) {
    Column(
        modifier =
        Modifier
            .background(MaterialTheme.colorScheme.background)
    ) {
        AppActionBarView(
            modifier = Modifier
                .fillMaxWidth(),
            headerText = stringResource(R.string.repository_details_title),
            showBackButton = true,
            onBackClick = {
                navController.popBackStack()
            }
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
