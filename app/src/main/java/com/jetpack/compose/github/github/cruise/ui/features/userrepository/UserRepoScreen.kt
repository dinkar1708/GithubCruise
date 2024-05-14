package com.jetpack.compose.github.github.cruise.ui.features.userrepository

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jetpack.compose.github.github.cruise.domain.model.User
import com.jetpack.compose.github.github.cruise.domain.model.UserProfile
import com.jetpack.compose.github.github.cruise.domain.model.UserRepo
import com.jetpack.compose.github.github.cruise.ui.features.userrepository.view.UserProfileView
import com.jetpack.compose.github.github.cruise.ui.features.userrepository.view.UserRepoListView
import com.jetpack.compose.github.github.cruise.ui.shared.AppActionBarView
import com.jetpack.compose.github.github.cruise.ui.shared.StateContentBox
import com.jetpack.compose.github.github.cruise.ui.theme.GithubCruiseTheme

/**
 * Created by Dinakar Maurya on 2024/05/12.
 */
@Composable
fun UserRepoScreen(
    viewModel: UserRepoScreenViewModel, onBackClick: () -> Unit
) {
    val viewState by viewModel.uiStateRepository.collectAsStateWithLifecycle()
    val viewStateProfile by viewModel.uiStateProfile.collectAsStateWithLifecycle()

    Column(
    ) {
        AppActionBarView(
            modifier = Modifier
                .fillMaxWidth(),
            headerText = viewState.selectedUser.login,
            showBackButton = true,
            onBackClick = onBackClick
        )
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                .background(MaterialTheme.colorScheme.background),
        ) {
            UserRepoListScreenContentsProfile(
                isLoading = viewStateProfile.isLoading,
                userProfile = viewStateProfile.userProfile,
                errorMessage = viewState.errorMessage
            )

            UserRepoListScreenContents(
                isLoading = viewState.isLoading,
                userRepoList = viewState.userRepoList,
                errorMessage = viewState.errorMessage
            )
        }
    }
}


@Composable
fun UserRepoListScreenContents(
    isLoading: Boolean,
    userRepoList: List<UserRepo>,
    errorMessage: String,
) {
    Box(
        modifier = Modifier.padding(top = 16.dp),
    ) {
        Text(
            text = "Repositories",
            style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onBackground),
        )
    }

    StateContentBox(
        Modifier.padding(top = 8.dp),
        isLoading = isLoading,
        errorMessage = errorMessage
    ) {
        UserRepoListView(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            userRepoList = userRepoList,
        )
    }
}

@Composable
fun UserRepoListScreenContentsProfile(
    isLoading: Boolean,
    userProfile: UserProfile,
    errorMessage: String,
) {
    StateContentBox(
        Modifier.padding(top = 8.dp),
        isLoading = isLoading,
        errorMessage = errorMessage
    ) {
        UserProfileView(
            userProfile
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UserRepoListHeaderPreview() {
    val user = User(
        id = 1,
        login = "loginuser",
        type = "User",
        url = "url",
        avatarUrl = "avatarUrl",
    )

    val userRepoList = mutableListOf(
        UserRepo(
            owner = UserRepo.Owner(login = "dinakr1708", avatarUrl = "url"),
            id = 1,
            name = "Repo",
            language = "JAVA",
            stargazersCount = "10",
            description = "Android Repo Desc", fork = false
        )
    )
    val userProfile = UserProfile(
        id = 1,
        followers = 10,
        following = 20,
        name = "Dinakar Maurya",
        avatarUrl = "url",
        login = "dinkar1708"
    )


    GithubCruiseTheme {
        Surface {
            Column {
                UserRepoListScreenContentsProfile(
                    userProfile = userProfile,
                    isLoading = false,
                    errorMessage = ""
                )
                UserRepoListScreenContents(
                    isLoading = false,
                    userRepoList = userRepoList,
                    errorMessage = ""
                )
            }
        }
    }
}