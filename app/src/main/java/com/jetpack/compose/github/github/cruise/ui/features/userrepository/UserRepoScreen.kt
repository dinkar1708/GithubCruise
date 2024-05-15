package com.jetpack.compose.github.github.cruise.ui.features.userrepository

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
    viewModel: UserRepoScreenViewModel,  onBackClick: () -> Unit,
    openRepoDetails: (String) -> Unit
) {
    val viewState by viewModel.uiStateRepository.collectAsStateWithLifecycle()
    val viewStateProfile by viewModel.uiStateProfile.collectAsStateWithLifecycle()

    Column (modifier =
    Modifier
        .background(MaterialTheme.colorScheme.background)){
        AppActionBarView(
            modifier = Modifier
                .fillMaxWidth(),
            headerText = viewState.selectedUser.login,
            showBackButton = true,
            onBackClick = onBackClick
        )

        UserRepoListScreenContentsProfile(
            isLoading = viewStateProfile.isLoading,
            userProfile = viewStateProfile.userProfile,
            errorMessage = viewStateProfile.errorMessage
        )

        HorizontalDivider(modifier = Modifier.padding(top = 16.dp))

        UserRepoListScreenContents(
            isLoading = viewState.isLoading,
            userRepoList = viewState.userRepoList,
            errorMessage = viewState.errorMessage,
            isShowForkRepo =
            {
                viewModel.filterRepositories(it)
            },
            openRepoDetails = openRepoDetails
        )
    }
}


@Composable
fun UserRepoListScreenContents(
    isLoading: Boolean,
    userRepoList: List<UserRepo>,
    errorMessage: String,
    isShowForkRepo: (Boolean) -> Unit,
    openRepoDetails: (String) -> Unit
) {
    var isShowingFork by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .background(MaterialTheme.colorScheme.background)
        ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "${userRepoList.size} Repositories",
                style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onBackground),
            )
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = isShowingFork,
                onCheckedChange = {
                    isShowingFork = it
                    isShowForkRepo(isShowingFork)
                }
            )
            Text(
                text = "${if (isShowingFork) "On" else "Off"} Fork",
                style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onBackground),
            )
        }

    }

    StateContentBox(
        isLoading = isLoading,
        errorMessage = errorMessage
    ) {
        UserRepoListView(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            userRepoList = userRepoList,
            openRepoDetails = openRepoDetails
        )
    }
}

@Composable
fun UserRepoListScreenContentsProfile(
    isLoading: Boolean,
    userProfile: UserProfile?,
    errorMessage: String,
) {
    StateContentBox(
        isLoading = isLoading,
        errorMessage = errorMessage
    ) {
        // assume user profile is not null at this point
        if (userProfile != null) {
            UserProfileView(
                userProfile
            )
        }
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
                    errorMessage = "",
                    isShowForkRepo = {},
                    openRepoDetails = {}
                )
            }
        }
    }
}
