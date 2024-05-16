package com.jetpack.compose.github.github.cruise.ui.features.userrepository.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpack.compose.github.github.cruise.domain.model.UserRepo
import com.jetpack.compose.github.github.cruise.ui.theme.GithubCruiseTheme

/**
 * Created by Dinakar Maurya on 2024/05/14.
 */
@Composable
fun UserRepoListView(
    modifier: Modifier, userRepoList: List<UserRepo>,
    openRepoDetails: (String) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(userRepoList.size) { index ->
            RepositoryListItem(
                userRepo = userRepoList[index],
                openRepoDetails = openRepoDetails
            )
            HorizontalDivider(color = MaterialTheme.colorScheme.onBackground)
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
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Language: ",
                    style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.primary),

                    )
                Text(
                    text = "${userRepo.language ?: "NA"}",
                    style = MaterialTheme.typography.titleSmall.copy(color = MaterialTheme.colorScheme.surfaceTint),

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
                    text = " Star: ",
                    style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.primary),
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "${userRepo.stargazersCount}",
                    style = MaterialTheme.typography.titleSmall.copy(color = MaterialTheme.colorScheme.surfaceTint),
                    overflow = TextOverflow.Ellipsis
                )
            }
            if (!userRepo.description.isNullOrBlank()) {
                Text(
                    text = userRepo.description,
                    style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.surfaceTint),
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
                description = "Android Repo Desc",
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