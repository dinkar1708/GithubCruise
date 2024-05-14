package com.jetpack.compose.github.github.cruise.ui.features.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpack.compose.github.github.cruise.di.DefaultDispatcher
import com.jetpack.compose.github.github.cruise.domain.usecase.SearchRepositoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Dinakar Maurya on 2024/05/12.
 */
@HiltViewModel
class UsersListViewModel @Inject constructor(
    private val searchRepositoryUseCase: SearchRepositoryUseCase,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _uiState = MutableStateFlow(UsersListState())
    val uiState: StateFlow<UsersListState> = _uiState.asStateFlow()

    // input search text
    private var userName: String = "dinkar1"

    // pagination variables
    private var page = 1
    private val pageSize = 20
    private var currentInputSearchTotalResultSize = 0
    private var isLoadingApiData = false
    // pagination variables end

    init {
        Timber.d("$TAG Initial load users.....")
        loadUsers() // TODO remove at the end
    }

    fun updateLastVisibleIndex(index: Int) {
        _uiState.update { _uiState.value.copy(lastVisibleItemIndex = index) }
    }

    fun updateInputString(inputString: String) {
        Timber.d("$TAG updateInputString() called...")
        userName = inputString
        // Reset page number when input string changes
        page = 1
        currentInputSearchTotalResultSize = 0
        isLoadingApiData = true
        _uiState.update {
            // reset all things
            UsersListState()
        }
        loadUsers()
    }

    /**
     * public function to trigger next page data
     */
    fun loadNextPage() {
        // Check if not already loading
        if (!isLoadingApiData && shouldLoadNextPage()) {
            isLoadingApiData = true
            page++
            Timber.d("$TAG loadNextPage() called... loading data for page $page now")
            loadUsers()
        } else {
            Timber.d("loadNextPage() No more data to load current page $page or already loading")
        }
    }

    private fun shouldLoadNextPage(): Boolean {
        // Check if the total count of items has been reached
        return page * pageSize < currentInputSearchTotalResultSize
    }

    private fun loadUsers() = viewModelScope.launch(dispatcher) {
        // keep original list
        _uiState.update {
            _uiState.value.copy(
                isLoading = true,
                userList = _uiState.value.userList
            )
        }
        try {
            if (userName == null || userName.isEmpty()) {
                _uiState.update {
                    // update UI
                    UsersListState(
                        errorMessage = "Input user name to search",
                        isLoading = false
                    )
                }
                return@launch
            }

            val userList = searchRepositoryUseCase.searchUsers(userName, page, pageSize)
                .catch { exception ->
                    _uiState.update {
                        // update UI
                        UsersListState(
                            errorMessage = exception.localizedMessage
                                ?: "Error searching user list",
                            isLoading = false
                        )
                    }
                    isLoadingApiData = false
                }
            userList.collect { searchUser ->
                // update total result found
                currentInputSearchTotalResultSize = searchUser.totalCount
                // current user list data to do processing
                val currentUserList = _uiState.value.userList
                // update user list
                val newUserList = if (searchUser.users.isNotEmpty()) {
                    if (page == 1) searchUser.users else currentUserList.plus(searchUser.users)
                } else {
                    currentUserList
                }
                // update UI
                _uiState.value = _uiState.value.copy(isLoading = false, userList = newUserList)
                Timber.d("$TAG UI has been updated after api data is received...")
                isLoadingApiData = false
            }
        } catch (e: Exception) {
            // Handle network-related exceptions here
            isLoadingApiData = false
            // update UI
            _uiState.update {
                UsersListState(
                    errorMessage = "Network error: ${e.localizedMessage}",
                    isLoading = false
                )
            }
        }
    }

    companion object {
        private const val TAG = "UsersListViewModel"
    }
}


