package com.jetpack.compose.github.github.cruise.ui.features.userrepository.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetpack.compose.github.github.cruise.domain.model.UserRepo
import com.jetpack.compose.github.github.cruise.ui.theme.GithubCruiseTheme

/**
 * Created by Dinakar Maurya on 2024/05/14.
 */
@Composable
fun UserRepoListView(modifier: Modifier, userRepoList: List<UserRepo>) {
    LazyColumn(modifier = modifier) {
        items(userRepoList.size) { index ->
            RepositoryListItem(
                userRepo = userRepoList[index]
            )
            Divider(color = MaterialTheme.colorScheme.onBackground)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepositoryListItem(userRepo: UserRepo) {
//    val clicked = remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
            .clickable {
//                clicked.value = !clicked.value
            }
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),

        ) {
        Row(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background),
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
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 18.sp,
                    ),
                )


                Text(
                    text = "Language ${userRepo.language}",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.surfaceTint,
                        fontSize = 14.sp,
                    ),
                )
                Text(
                    text = "* ${userRepo.stargazersCount}",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.surfaceTint,
                        fontSize = 14.sp,
                    ),
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = userRepo.description ?: "",
                    color = MaterialTheme.colorScheme.surfaceTint,
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 14.sp,
                    ),
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
                owner = UserRepo.Owner(login = "dinakr1708", avatarUrl = "url"),
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
        )
    }
}