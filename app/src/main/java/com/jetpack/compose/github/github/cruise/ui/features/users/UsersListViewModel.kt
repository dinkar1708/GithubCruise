package com.jetpack.compose.github.github.cruise.ui.features.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpack.compose.github.github.cruise.di.DefaultDispatcher
import com.jetpack.compose.github.github.cruise.domain.usecase.SearchRepositoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
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

    init {
        loadUsers()
    }

    private fun loadUsers() = viewModelScope.launch(dispatcher) {
        try {
            searchRepositoryUseCase.searchUsers("dinkar1708") // TODO remove check with hard coded
                .catch { exception ->
                    // TODO show error on UI
                }
                .collect { userList ->
                    // TODO send data on UI
                }
        } catch (e: Exception) {
            // handle error and send on UI
        }
    }
}