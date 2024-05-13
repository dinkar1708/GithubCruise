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
    // private var userName: String? = null
    // TODO remove default value
    private var userName: String? = "dinkar1708"

    init {
        loadUsers()
    }

    fun updateInputString(inputString: String) {
        userName = inputString
        loadUsers()
    }

    private fun loadUsers() = viewModelScope.launch(dispatcher) {
        _uiState.update { UsersListState(isLoading = true) }

        try {
            if (userName == null || userName!!.isEmpty()) {
                _uiState.update {
                    UsersListState(
                        errorMessage = "Input user name to search",
                        isLoading = false
                    )
                }
                return@launch
            }

            searchRepositoryUseCase.searchUsers(userName!!)
                .catch { exception ->
                    _uiState.update {
                        UsersListState(
                            errorMessage = exception.localizedMessage
                                ?: "Error searching user list",
                            isLoading = false
                        )
                    }
                }
                .collect { userList ->
                    _uiState.update {
                        UsersListState(
                            userList = userList.users,
                            isLoading = false
                        )
                    }
                }
        } catch (e: Exception) {
            // Handle network-related exceptions here
            _uiState.update {
                UsersListState(
                    errorMessage = "Network error: ${e.localizedMessage}",
                    isLoading = false
                )
            }
        }
    }

}