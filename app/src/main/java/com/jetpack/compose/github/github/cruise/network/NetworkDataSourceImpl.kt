package com.jetpack.compose.github.github.cruise.network

import com.jetpack.compose.github.github.cruise.domain.model.SearchUser
import com.jetpack.compose.github.github.cruise.network.api.APIInterface

/**
 * Created by Dinakar Maurya on 2024/05/12
 */
class NetworkDataSourceImpl(
    private val api: APIInterface
) : NetworkDataSource {
    override suspend fun searchUser(
        userName: String, page: Int,
        pageSize: Int,
    ): SearchUser {
        return api.getSearchUsers(userName, page, pageSize)
    }
}