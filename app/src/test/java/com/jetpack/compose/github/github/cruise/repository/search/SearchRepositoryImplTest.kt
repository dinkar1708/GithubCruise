package com.jetpack.compose.github.github.cruise.repository.search

import com.jetpack.compose.github.github.cruise.domain.model.SearchUser
import com.jetpack.compose.github.github.cruise.domain.model.User
import com.jetpack.compose.github.github.cruise.network.NetworkDataSource
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Created by Dinakar Maurya on 2024/05/13
 */
// TODO
class SearchRepositoryImplTest {
    private val mockNetworkDataSource: NetworkDataSource = mockk()
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var repository: SearchRepositoryImpl

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
        repository = SearchRepositoryImpl(mockNetworkDataSource, testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test search Users API call success with incomplete result`() {
        runTest {
            val userName = "dinkar1708"
            // set mock data for user name
            coEvery { mockNetworkDataSource.searchUser(userName) } returns searchUser
            // now call mock api
            val resultFlow = repository.searchUsers(userName)
            val result = resultFlow.single()
            // for same user mock response and api response must be same
            assertEquals(searchUser, result)
            // incomplete result
            assertTrue(searchUser.incompleteResults)
        }
    }
}