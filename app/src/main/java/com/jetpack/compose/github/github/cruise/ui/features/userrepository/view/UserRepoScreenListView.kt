package com.jetpack.compose.github.github.cruise.ui.features.userrepository.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpack.compose.github.github.cruise.R
import com.jetpack.compose.github.github.cruise.domain.model.UserRepo
import com.jetpack.compose.github.github.cruise.ui.shared.HorizontalLineView
import com.jetpack.compose.github.github.cruise.ui.theme.GithubCruiseTheme

/**
 * Created by Dinakar Maurya on 2024/05/14.
 */
@Composable
fun UserRepoListView(
    modifier: Modifier,
    userRepoList: List<UserRepo>,
    openRepoDetails: (String) -> Unit
) {
    LazyColumn(modifier = modifier) {
        itemsIndexed(userRepoList) { _, userRepo ->
            key(userRepo.id) {
                RepositoryListItem(
                    userRepo = userRepo,
                    openRepoDetails = openRepoDetails
                )
                HorizontalLineView()
            }
        }
    }
}


@Composable
fun RepositoryListItem(userRepo: UserRepo, openRepoDetails: (String) -> Unit) {
//    val clicked = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .clickable {
                openRepoDetails(userRepo.htmlUrl)
            },
//     .background(color = if (clicked.value) Color.Blue else MaterialTheme.colorScheme.background),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp)
        ) {
            Text(
                text = userRepo.name,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                ),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(
                        R.string.user_repository_repo_list_language
                    ),
                    style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.primary),
                )
                Text(
                    text = userRepo.language ?: "NA",
                    style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.surfaceTint),
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    Icons.Filled.Star, "", tint = MaterialTheme.colorScheme.surfaceTint,
                )
                Text(
                    text = stringResource(R.string.user_repository_list_start),
                    style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.primary),
                )
                Text(
                    text = userRepo.stargazersCount,
                    style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.surfaceTint),
                )
            }
            if (!userRepo.description.isNullOrBlank()) {
                Text(
                    text = userRepo.description,
                    style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.surfaceTint),
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserRepositoryListPreview() {
    val repoList =
        mutableListOf(
            UserRepo(
                id = 1,
                name = "Repo",
                language = "JAVA",
                stargazersCount = "10",
                description = "Android Repo Desc Android Repo Desc Android Repo Desc",
                fork = false
            )
        )

    GithubCruiseTheme {
        UserRepoListView(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            userRepoList = repoList,
            openRepoDetails = {}
        )
    }
}