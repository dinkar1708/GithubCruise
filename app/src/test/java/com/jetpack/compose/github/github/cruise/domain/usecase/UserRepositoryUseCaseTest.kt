package com.jetpack.compose.github.github.cruise.domain.usecase

import com.jetpack.compose.github.github.cruise.domain.model.UserRepo
import com.jetpack.compose.github.github.cruise.repository.user.UserRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test

class UserRepositoryUseCaseTest {

    private val mockRepository: UserRepository = mockk()
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var userRepositoryUseCase: UserRepositoryUseCase

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        userRepositoryUseCase = UserRepositoryUseCase(mockRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test filter Not Forked UserRepositories `() = runTest {
        val userRepoList = mutableListOf(
            UserRepo(
                id = 1,
                name = "Repo",
                language = "JAVA",
                stargazersCount = "10",
                description = "Android Repo Desc",
                fork = false
            ),
            UserRepo(
                id = 1,
                name = "Fork Repo",
                language = "Kotlin",
                stargazersCount = "100",
                description = "Fork Repo Desc",
                fork = true
            )
        )
        // Given
        coEvery { mockRepository.getUserRepositories("dinkar1708", 1, 20) } returns flowOf(
            userRepoList
        )
        // When
        val result = userRepositoryUseCase.filterUserRepositories(
            false,
            "dinkar1708", 1, 20,
        )
            .single()
        // Then
        // should filter 1 from given input
        assertEquals(1, result.size)
        // must be not forked ie. false
        assertFalse(result.first().fork)
        assertEquals("Repo", result.first().name)
    }
}