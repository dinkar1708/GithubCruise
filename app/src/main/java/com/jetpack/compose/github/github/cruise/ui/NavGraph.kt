package com.jetpack.compose.github.github.cruise.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jetpack.compose.github.github.cruise.ui.MainDestinations.SPLASH_SCREEN_ROUTE
import com.jetpack.compose.github.github.cruise.ui.MainDestinations.USERS_LIST_SCREEN_ROUTE
import com.jetpack.compose.github.github.cruise.ui.MainDestinations.USER_REPO_DETAILS_SCREEN_ROUTE
import com.jetpack.compose.github.github.cruise.ui.MainDestinations.USER_REPO_SCREEN_ROUTE
import com.jetpack.compose.github.github.cruise.ui.MainDestinationsParams.USER_REPO_DETAILS_SCREEN_PARAM
import com.jetpack.compose.github.github.cruise.ui.MainDestinationsParams.USER_REPO_DETAIL_SCREEN_LOGIN_PARAM
import com.jetpack.compose.github.github.cruise.ui.features.repodetails.RepoDetailsScreen
import com.jetpack.compose.github.github.cruise.ui.features.splash.SplashScreen
import com.jetpack.compose.github.github.cruise.ui.features.userrepository.UserRepoScreen
import com.jetpack.compose.github.github.cruise.ui.features.userrepository.UserRepoScreenViewModel
import com.jetpack.compose.github.github.cruise.ui.features.users.UsersListScreen
import com.jetpack.compose.github.github.cruise.ui.features.users.UsersListViewModel
import com.jetpack.compose.github.github.cruise.ui.shared.utils.CommonUtils

/**
 * Created by Dinakar Maurya on 2024/05/12.
 */

object MainDestinations {
    const val SPLASH_SCREEN_ROUTE = "splash"
    const val USERS_LIST_SCREEN_ROUTE = "users_list"
    const val USER_REPO_SCREEN_ROUTE = "user_repo"
    const val USER_REPO_DETAILS_SCREEN_ROUTE = "user_repo_details"
}

object MainDestinationsParams {
    const val USER_REPO_DETAIL_SCREEN_LOGIN_PARAM = "login"
    const val USER_REPO_DETAILS_SCREEN_PARAM = "html_url"
}

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String = SPLASH_SCREEN_ROUTE
) {

    NavHost(navController = navController, startDestination = startDestination) {
        composable(SPLASH_SCREEN_ROUTE) {
            SplashScreen(navController)
        }

        composable(USERS_LIST_SCREEN_ROUTE) {
            val viewModel: UsersListViewModel = hiltViewModel()
            UsersListScreen(navController, viewModel = viewModel)
        }

        composable(
            "$USER_REPO_SCREEN_ROUTE/{$USER_REPO_DETAIL_SCREEN_LOGIN_PARAM}",
            arguments = listOf(navArgument(USER_REPO_DETAIL_SCREEN_LOGIN_PARAM) {
                type = NavType.StringType
            })
        ) {
            val viewModel: UserRepoScreenViewModel = hiltViewModel()
            UserRepoScreen(
                navController,
                viewModel = viewModel,
                it.arguments?.getString(USER_REPO_DETAIL_SCREEN_LOGIN_PARAM) ?: ""
            )
        }

        composable(
            "$USER_REPO_DETAILS_SCREEN_ROUTE/{$USER_REPO_DETAILS_SCREEN_PARAM}",
            arguments = listOf(navArgument(USER_REPO_DETAILS_SCREEN_PARAM) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val decodedUrl = CommonUtils.decodeUrl(
                backStackEntry.arguments?.getString(USER_REPO_DETAILS_SCREEN_PARAM) ?: ""
            )
            RepoDetailsScreen(navController, htmlUrl = decodedUrl)
        }
    }
}
