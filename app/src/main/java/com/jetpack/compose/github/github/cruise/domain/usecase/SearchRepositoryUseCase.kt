package com.jetpack.compose.github.github.cruise.domain.usecase

import com.jetpack.compose.github.github.cruise.domain.model.SearchUser
import com.jetpack.compose.github.github.cruise.repository.search.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Dinakar Maurya on 2024/05/13
 */
class SearchRepositoryUseCase @Inject constructor(private val searchRepository: SearchRepository) {
    suspend fun searchUsers(
        userName: String
    ): Flow<SearchUser> =
        searchRepository.searchUsers(userName = userName)
}
