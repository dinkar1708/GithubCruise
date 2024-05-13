package com.jetpack.compose.github.github.cruise.ui.features.users

import com.jetpack.compose.github.github.cruise.domain.model.SearchUser
import com.jetpack.compose.github.github.cruise.domain.model.User
import com.jetpack.compose.github.github.cruise.domain.usecase.SearchRepositoryUseCase
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

    // mock data
    private val user = User(
        id = 1,
        login = "dinkar1708",
        type = "User",
        avatarUrl = "https://avatars.githubusercontent.com/u/14831652?v=4",
    )
    private val searchUser = SearchUser(2, true, mutableListOf(user))

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
    fun `search user - empty input and state error`() = runTest {
        // When
        // empty text
        viewModel.updateInputString("")
        advanceUntilIdle() // Advance time to process the flow
        // Then
        val state = viewModel.uiState.value
        Assert.assertEquals(false, state.isLoading)
        Assert.assertEquals("Input user name to search", state.errorMessage)
    }

    @Test
    fun `search user - data loaded`() = runTest {
        // https://developer.android.com/kotlin/coroutines/test#inject-scope
        // Given
        coEvery {
            mockSearchRepositoryUseCase.searchUsers(userName = "dinkar1708", page = 1, pageSize = 10)
        } returns flowOf(searchUser)

        // When
        viewModel.updateInputString("dinkar1708")

        advanceUntilIdle() // Advance time to process the flow
        // Then
        val state = viewModel.uiState.value
//        Assert.assertEquals(false, state.isLoading)
        // TODO fix below test case
//        assertFalse(state.userList.isNotEmpty())
    }
}