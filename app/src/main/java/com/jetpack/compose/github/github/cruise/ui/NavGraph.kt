package com.jetpack.compose.github.github.cruise.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jetpack.compose.github.github.cruise.ui.features.users.UsersListScreen
import com.jetpack.compose.github.github.cruise.ui.features.users.UsersListViewModel

/**
 * Created by Dinakar Maurya on 2024/05/12.
 */

object MainDestinations {
    const val USERS_LIST_SCREEN_ROUTE = "users_list"
}

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String = MainDestinations.USERS_LIST_SCREEN_ROUTE
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(MainDestinations.USERS_LIST_SCREEN_ROUTE) {
            val viewModel: UsersListViewModel = hiltViewModel()
            UsersListScreen(viewModel = viewModel)
        }
    }
}