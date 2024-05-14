package com.jetpack.compose.github.github.cruise.ui.features.userrepository.state

import com.jetpack.compose.github.github.cruise.domain.model.UserProfile

/**
 * Created by Dinakar Maurya on 2024/05/14.
 */
data class UserRepoScreenProfileState(
    val userProfile: UserProfile = UserProfile(id = 0, avatarUrl = "", login = ""),
    val isLoading: Boolean = false,
    val errorMessage: String = ""
)