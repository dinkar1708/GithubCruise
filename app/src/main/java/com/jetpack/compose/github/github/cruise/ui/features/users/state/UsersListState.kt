package com.jetpack.compose.github.github.cruise.ui.features.users.state

import com.jetpack.compose.github.github.cruise.domain.model.User

/**
 * Created by Dinakar Maurya on 2024/05/13.
 */
data class UsersListState(
    val userList: List<User> = emptyList(),
    val lastVisibleItemIndex: Int = 0,
    val isLoading: Boolean = false,
    val errorMessage: String = ""
)