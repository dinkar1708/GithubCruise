package com.jetpack.compose.github.github.cruise.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jetpack.compose.github.github.cruise.domain.model.User
import com.jetpack.compose.github.github.cruise.ui.features.repodetails.RepoDetailsScreen
import com.jetpack.compose.github.github.cruise.ui.features.userrepository.UserRepoScreen
import com.jetpack.compose.github.github.cruise.ui.features.userrepository.UserRepoScreenViewModel
import com.jetpack.compose.github.github.cruise.ui.features.users.UsersListScreen
import com.jetpack.compose.github.github.cruise.ui.features.users.UsersListViewModel

/**
 * Created by Dinakar Maurya on 2024/05/12.
 */

object MainDestinations {
    const val USERS_LIST_SCREEN_ROUTE = "users_list"
    const val USER_REPO_SCREEN_ROUTE = "user_repo"
    const val USER_REPO_DETAIL_SCREEN_ROUTE = "user_repo_details"
}

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String = MainDestinations.USERS_LIST_SCREEN_ROUTE
) {
    var searchedUser by remember { mutableStateOf(User()) }
    var repositoryUrl by remember { mutableStateOf("") }

    NavHost(navController = navController, startDestination = startDestination) {
        composable(MainDestinations.USERS_LIST_SCREEN_ROUTE) {
            val viewModel: UsersListViewModel = hiltViewModel()
            UsersListScreen(viewModel = viewModel) {
                searchedUser = it
                navController.navigate(MainDestinations.USER_REPO_SCREEN_ROUTE)
            }
        }

        composable(MainDestinations.USER_REPO_SCREEN_ROUTE) {
            val viewModel: UserRepoScreenViewModel = hiltViewModel()
            // prevent search user across screen rotation
            LaunchedEffect(key1 = searchedUser) {
                viewModel.loadApiData(searchedUser)
            }
            UserRepoScreen(viewModel = viewModel,
                onBackClick = { navController.popBackStack() },
                openRepoDetails = { url ->
                    repositoryUrl = url
                    navController.navigate(MainDestinations.USER_REPO_DETAIL_SCREEN_ROUTE)
                }
            )
        }

        composable(MainDestinations.USER_REPO_DETAIL_SCREEN_ROUTE) {
            RepoDetailsScreen(htmlUrl = repositoryUrl,
                onBackClick = { navController.popBackStack() })
        }
    }
}
