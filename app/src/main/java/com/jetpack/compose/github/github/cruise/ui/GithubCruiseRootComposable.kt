package com.jetpack.compose.github.github.cruise.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

/**
 * Created by Dinakar Maurya on 2024/05/12.
 */
@Composable
fun GithubCruiseRootComposable() {
    val navController = rememberNavController()
    NavGraph(
        navController = navController,
        startDestination = MainDestinations.USERS_LIST_SCREEN_ROUTE
    )
}