package com.jetpack.compose.github.github.cruise.ui.features.users

import com.jetpack.compose.github.github.cruise.domain.model.SearchUser
import com.jetpack.compose.github.github.cruise.domain.model.User
import com.jetpack.compose.github.github.cruise.domain.model.UserRepo
import com.jetpack.compose.github.github.cruise.domain.usecase.SearchRepositoryUseCase
import com.jetpack.compose.github.github.cruise.network.model.ApiError
import com.jetpack.compose.github.github.cruise.network.model.ApiErrorResponse
import com.jetpack.compose.github.github.cruise.ui.features.userrepository.state.UserRepoViewListState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test


/**
 * Created by Dinakar Maurya on 2024/05/13
 */
// TODO
class UsersListViewModelTest {
    private val mockSearchRepositoryUseCase: SearchRepositoryUseCase = mockk()
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: UsersListViewModel

    private var page = 1
    private val pageSize = 10

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = UsersListViewModel(mockSearchRepositoryUseCase, testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test searchUsers() for loading end and error message on input text search`() =
        runTest {
            // Given
            val inputText = ""
            // When
            viewModel.searchUsers( inputText)

            // Advance time to process the flow
            advanceUntilIdle()

            // Then

            // test
            // test state expected value and correct message
            val stateUserList = viewModel.uiState.value
            // test loading
            Assert.assertEquals(false, stateUserList.isLoading)
            // check that got correct error message
            Assert.assertEquals("Input user name to search", stateUserList.errorMessage)
        }

    @Test
    fun `test searchUsers() for user valid list on search api call success`() =
        runTest {
            // mock data
            // mock data
             val user = User(
                id = 1,
                login = "dinkar1708",
                avatarUrl = "https://avatars.githubusercontent.com/u/14831652?v=4",
            )
             val searchUser = SearchUser(2, true, mutableListOf(user))
            val inputText = "dinkar1708"
            // Given
            coEvery {
                mockSearchRepositoryUseCase.searchUsers(
                    inputText,
                    page,
                    pageSize
                )
            } returns flowOf(searchUser)

            // When
            viewModel.searchUsers( inputText)

            // Advance time to process the flow
            advanceUntilIdle()

            // Then
            // test state expected value and correct message
            // checking loading cancelled
            val stateUserList = viewModel.uiState.value
            // test
            Assert.assertEquals(false, stateUserList.isLoading)
            // check that got data
            Assert.assertEquals(searchUser.users, stateUserList.userList)
        }


    @Test
    fun `test searchUsers() for user no matching user found on api call success`() =
        runTest {
            // mock data
            val inputText = "invalid-user-name-invalid-username-invalid"
            // Given
            val searchUser = SearchUser(0, false, emptyList())
            coEvery {
                mockSearchRepositoryUseCase.searchUsers(
                    inputText,
                    page,
                    pageSize
                )
            } returns flowOf(searchUser)

            // When
            viewModel.searchUsers( inputText)

            // Advance time to process the flow
            advanceUntilIdle()

            // Then
            // test state expected value and correct message
            // checking loading cancelled
            val stateUserList = viewModel.uiState.value
            // test
            // test loading cancelled
            Assert.assertEquals(false, stateUserList.isLoading)
            // test error message
            Assert.assertEquals("Your search did not match any user!", stateUserList.errorMessage)
            // check that 0 results
            Assert.assertTrue(stateUserList.userList.isEmpty())
        }

    @Test
    fun `test searchUsers() for loading error and error message on api call rate limit error`() =
        runTest {
            // mock data
            val inputText = "dinkar1708"
            // Given
            coEvery {
                mockSearchRepositoryUseCase.searchUsers(
                    inputText,
                    page,
                    pageSize
                )
            } throws ApiError.ApiException(
                ApiErrorResponse(
                    message = "API rate limit exceeded for 134.180.235.148. (But here's the good news: Authenticated requests get a higher rate limit. Check out the documentation for more details.",
                    documentationUrl = "https://docs.github.com/rest/overview/resources-in-the-rest-api#rate-limiting"
                )
            )

            // When
            viewModel.searchUsers(inputText)

            // Advance time to process the flow
            advanceUntilIdle()

            // Then
            // test state expected value and correct message
            // checking loading cancelled
            val stateUserList = viewModel.uiState.value
            // test
            // test loading cancelled
            Assert.assertEquals(false, stateUserList.isLoading)
            // test error message
            Assert.assertEquals("API rate limit exceeded for 134.180.235.148. (But here's the good news: Authenticated requests get a higher rate limit. Check out the documentation for more details.", stateUserList.errorMessage)
        }

    @Test
    fun `test searchUsers() for loading error and error message on api call network error error`() =
        runTest {
            // mock data
            val inputText = "dinkar1708"
            // Given
            coEvery {
                mockSearchRepositoryUseCase.searchUsers(
                    inputText,
                    page,
                    pageSize
                )
            } throws  ApiError.NetworkError("Unknown host error!")

            // When
            viewModel.searchUsers(inputText)

            // Advance time to process the flow
            advanceUntilIdle()

            // Then
            // test state expected value and correct message
            // checking loading cancelled
            val stateUserList = viewModel.uiState.value
            // test
            // test loading cancelled
            Assert.assertEquals(false, stateUserList.isLoading)
            // test error message
            Assert.assertEquals("Unknown host error!", stateUserList.errorMessage)
        }
}