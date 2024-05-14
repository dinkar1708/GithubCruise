package com.jetpack.compose.github.github.cruise.ui.features.userrepository

import com.jetpack.compose.github.github.cruise.domain.model.User
import com.jetpack.compose.github.github.cruise.domain.model.UserProfile
import com.jetpack.compose.github.github.cruise.domain.model.UserRepo
import com.jetpack.compose.github.github.cruise.domain.usecase.UserRepositoryUseCase
import com.jetpack.compose.github.github.cruise.network.model.ApiError
import com.jetpack.compose.github.github.cruise.network.model.ApiErrorResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UserRepoScreenViewModelTest {
    private val mockUserRepositoryUseCase: UserRepositoryUseCase = mockk()
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: UserRepoScreenViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = UserRepoScreenViewModel(mockUserRepositoryUseCase, testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test loadApiData uiStateProfile - loading cancelled after api call failed`() = runTest {
        // mock data
         val user = User(
            id = 1,
            login = "dinkar1708",
            type = "User",
            avatarUrl = "https://avatars.githubusercontent.com/u/14831652?v=4",
        )
        // Given
        coEvery { mockUserRepositoryUseCase.getUserProfile(user.login) } throws ApiError.ApiException(
            ApiErrorResponse(
                message = "API rate limit exceeded for 134.180.235.148. (But here's the good news: Authenticated requests get a higher rate limit. Check out the documentation for more details.",
                documentationUrl = "https://docs.github.com/rest/overview/resources-in-the-rest-api#rate-limiting"
            )
        )
        // When
        // empty text
        viewModel.loadApiData(user)

        // Advance time to process the flow
        advanceUntilIdle()
        val state = viewModel.uiStateProfile.value
        // Then
        // checking loading without mock data
        Assert.assertEquals(false, state.isLoading)
        // Network error is added from view model so lets test it.
        Assert.assertEquals("Network error: API rate limit exceeded for 134.180.235.148. (But here's the good news: Authenticated requests get a higher rate limit. Check out the documentation for more details.",
            state.errorMessage)
    }

    @Test
    fun `test loadApiData uiStateProfile -api call success`() = runTest {
        // mock data
        val user = User(
            id = 1,
            login = "dinkar1708",
            type = "User",
            avatarUrl = "https://avatars.githubusercontent.com/u/14831652?v=4",
        )
        val userProfile = UserProfile(
            id = 1,
            followers = 10,
            following = 20,
            name = "Dinakar Maurya",
            avatarUrl = "url",
            login = "dinkar1708"
        )
        // Given
        coEvery { mockUserRepositoryUseCase.getUserProfile(user.login) } returns flowOf( userProfile)
        // When
        // empty text
        viewModel.loadApiData(user)

        // Advance time to process the flow
        advanceUntilIdle()
        val state = viewModel.uiStateProfile.value
        // Then
        // checking loading cancelled
        Assert.assertEquals(false, state.isLoading)
        // check that got the user profile in the state
        Assert.assertEquals(userProfile, state.userProfile)
    }

// TODO fix
//
//    @Test
//    fun `test loadApiData filterNotForkedUserRepositories - loading cancelled after api call failed`() = runTest {
//        // mock data
//        val user = User(
//            id = 1,
//            login = "dinkar1708",
//            type = "User",
//            avatarUrl = "https://avatars.githubusercontent.com/u/14831652?v=4",
//        )
//        // Given
//        coEvery { mockUserRepositoryUseCase.filterNotForkedUserRepositories(user.login, 1, 20) } throws ApiError.ApiException(
//            ApiErrorResponse(
//                message = "API rate limit exceeded for 134.180.235.148. (But here's the good news: Authenticated requests get a higher rate limit. Check out the documentation for more details.",
//                documentationUrl = "https://docs.github.com/rest/overview/resources-in-the-rest-api#rate-limiting"
//            )
//        )
//        // When
//        // empty text
//        viewModel.loadApiData(user)
//
//        // Advance time to process the flow
//        advanceUntilIdle()
//        val state = viewModel.uiStateRepository.value
//        // Then
//        // checking loading without mock data
//        Assert.assertEquals(false, state.isLoading)
//        // TODO fix below error
//        // Expected :API rate limit exceeded for 134.180.235.148. (But here's the good news: Authenticated requests get a higher rate limit. Check out the documentation for more details.
//        // Actual   :Network error: no answer found for: UserRepositoryUseCase(#1).filterNotForkedUserRepositories(dinkar1708, 1, 40, continuation {})
//        Assert.assertEquals("API rate limit exceeded for 134.180.235.148. (But here's the good news: Authenticated requests get a higher rate limit. Check out the documentation for more details.",
//            state.errorMessage)
//    }

    // TODO fix
//
//    @Test
//    fun `test loadApiData uiStateRepository -api call success`() = runTest {
//        // mock data
//        val user = User(
//            id = 1,
//            login = "dinkar1708",
//            type = "User",
//            avatarUrl = "https://avatars.githubusercontent.com/u/14831652?v=4",
//        )
//        val userRepoList = mutableListOf(
//            UserRepo(
//                owner = UserRepo.Owner(login = "dinakr1708", avatarUrl = "url"),
//                id = 1,
//                name = "Repo",
//                language = "JAVA",
//                stargazersCount = "10",
//                description = "Android Repo Desc",
//                fork = true
//            ),
//        )
//        // Given
//        // TODO fix below error
//        // no answer found for: UserRepositoryUseCase(#1).filterNotForkedUserRepositories(dinkar1708, 1, 40, continuation {})
//        coEvery { mockUserRepositoryUseCase.filterNotForkedUserRepositories(user.login, 1, 10) } returns flowOf( userRepoList)
//        // When
//        // empty text
//        viewModel.loadApiData(user)
//
//        // Advance time to process the flow
//        advanceUntilIdle()
//        val state = viewModel.uiStateRepository.value
//        // Then
//        // checking loading cancelled
//        Assert.assertEquals(false, state.isLoading)
//        // check that got same data in the state
//        Assert.assertEquals(userRepoList.size, state.userRepoList.size)
//    }
}