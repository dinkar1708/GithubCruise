package com.jetpack.compose.github.github.cruise.ui.features.userrepository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpack.compose.github.github.cruise.di.DefaultDispatcher
import com.jetpack.compose.github.github.cruise.domain.model.User
import com.jetpack.compose.github.github.cruise.domain.usecase.UserRepositoryUseCase
import com.jetpack.compose.github.github.cruise.ui.features.userrepository.state.UserRepoScreenProfileState
import com.jetpack.compose.github.github.cruise.ui.features.userrepository.state.UserRepoViewListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Dinakar Maurya on 2024/05/14.
 */
@HiltViewModel
class UserRepoScreenViewModel @Inject constructor(
    private val userRepositoryUseCase: UserRepositoryUseCase,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _uiStateRepository = MutableStateFlow(UserRepoViewListState())
    val uiStateRepository: StateFlow<UserRepoViewListState> = _uiStateRepository.asStateFlow()

    private val _uiStateProfile = MutableStateFlow(UserRepoScreenProfileState())
    val uiStateProfile: StateFlow<UserRepoScreenProfileState> = _uiStateProfile.asStateFlow()

    fun loadApiData(user: User) = viewModelScope.launch(dispatcher) {
        loadUserProfile(user)
        loadUserRepositories(user)
    }

    private suspend fun loadUserProfile(user: User) {
        _uiStateProfile.update { it.copy(isLoading = true) }

        try {
            val userProfile = userRepositoryUseCase.getUserProfile(login = user.login)
                .catch { exception ->
                    Timber.e("viewmodel loadUserProfile error $exception")
                    _uiStateProfile.update {
                        it.copy(
                            errorMessage = exception.toString(),
                            isLoading = false
                        )
                    }
                }
                .singleOrNull() ?: return
            _uiStateProfile.update {
                it.copy(
                    userProfile = userProfile,
                    isLoading = false
                )
            }
        } catch (e: Exception) {
            Timber.e("viewmodel loadUserProfile unexpected $e")
            _uiStateProfile.update {
                it.copy(
                    errorMessage = "Network error: ${e.localizedMessage}",
                    isLoading = false
                )
            }
        }
    }

    private suspend fun loadUserRepositories(user: User) {
        _uiStateRepository.update { it.copy(isLoading = true) }

        try {
            val repositories =
                userRepositoryUseCase.filterNotForkedUserRepositories(login = user.login, 1, 40)
                    .catch { exception ->
                        Timber.e("viewmodel loadUserRepositories $exception")
                        _uiStateRepository.update {
                            it.copy(
                                errorMessage = exception.toString(),
                                isLoading = false
                            )
                        }
                    }
                    .singleOrNull() ?: return

            if (repositories.isEmpty()) {
                _uiStateRepository.update {
                    it.copy(
                        selectedUser = user,
                        userRepoList = emptyList(),
                        isLoading = false,
                        errorMessage = "There are no non-forked repositories by this user."
                    )
                }
                return
            }
            _uiStateRepository.update {
                it.copy(
                    selectedUser = user,
                    userRepoList = repositories,
                    isLoading = false
                )
            }
        } catch (e: Exception) {
            Timber.e("viewmodel loadUserRepositories unexpected $e")
            _uiStateRepository.update {
                it.copy(
                    errorMessage = "Network error: ${e.localizedMessage}",
                    isLoading = false
                )
            }
        }
    }
}
