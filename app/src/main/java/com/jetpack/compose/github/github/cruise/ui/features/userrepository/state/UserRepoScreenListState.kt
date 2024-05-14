package com.jetpack.compose.github.github.cruise.ui.features.userrepository.state

import com.jetpack.compose.github.github.cruise.domain.model.User
import com.jetpack.compose.github.github.cruise.domain.model.UserRepo

/**
 * Created by Dinakar Maurya on 2024/05/14.
 */
data class UserRepoViewListState(
    val selectedUser: User = User(),
    val userRepoList: List<UserRepo> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String = ""
)