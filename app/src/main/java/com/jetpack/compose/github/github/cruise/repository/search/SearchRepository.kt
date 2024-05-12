package com.jetpack.compose.github.github.cruise.repository.search

import com.jetpack.compose.github.github.cruise.domain.model.SearchUser
import kotlinx.coroutines.flow.Flow

/**
 * Created by Dinakar Maurya on 2024/05/13
 */
interface SearchRepository {
    suspend fun searchUsers(userName: String): Flow<SearchUser>
}