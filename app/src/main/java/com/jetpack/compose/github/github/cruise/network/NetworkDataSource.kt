package com.jetpack.compose.github.github.cruise.network

import com.jetpack.compose.github.github.cruise.domain.model.SearchUser

/**
 * Created by Dinakar Maurya on 2024/05/12
 */
interface NetworkDataSource {
    suspend fun searchUser(
        userName: String, page: Int,
        pageSize: Int,
    ): SearchUser
}