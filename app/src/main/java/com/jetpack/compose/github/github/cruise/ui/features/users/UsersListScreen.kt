package com.jetpack.compose.github.github.cruise.ui.features.users

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.jetpack.compose.github.github.cruise.R
import com.jetpack.compose.github.github.cruise.domain.model.User
import com.jetpack.compose.github.github.cruise.ui.MainDestinations.USER_REPO_SCREEN_ROUTE
import com.jetpack.compose.github.github.cruise.ui.shared.AppActionBarView
import com.jetpack.compose.github.github.cruise.ui.shared.StateContentBox
import com.jetpack.compose.github.github.cruise.ui.theme.GithubCruiseTheme


/**
 * Created by Dinakar Maurya on 2024/05/12.
 */
@Composable
fun UsersListScreen(
    navController: NavHostController,
    viewModel: UsersListViewModel
) {
    val viewState by viewModel.uiState.collectAsStateWithLifecycle()

    UsersListScreenContent(
        isLoading = viewState.isLoading,
        userList = viewState.userList,
        lastVisibleItemIndex = viewState.lastVisibleItemIndex,
        errorMessage = viewState.errorMessage,
        onItemClick = {
            navController.navigate("${USER_REPO_SCREEN_ROUTE}/${it.login}")
        },
        onSearchSubmitted = { viewModel.searchUsers(it) },
        onClearInput = {
            // clear text
            viewModel.searchUsers("")
        },
        onListScrolledToEnd = { i ->
            viewModel.updateLastVisibleIndex(i)
            viewModel.loadNextPage()
        }
    )
}

@Composable
fun UsersListScreenContent(
    isLoading: Boolean,
    userList: List<User>,
    lastVisibleItemIndex: Int,
    errorMessage: String,
    onItemClick: (User) -> Unit,
    onSearchSubmitted: (String) -> Unit,
    onClearInput: () -> Unit,
    onListScrolledToEnd: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {

        AppActionBarView(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            headerText = stringResource(R.string.users_page_title),
            showBackButton = false
        )
        SearchBar(onSearchSubmitted = onSearchSubmitted, onClearInput)

        StateContentBox(
            Modifier.padding(top = 8.dp),
            isLoading = isLoading,
            errorMessage = errorMessage
        ) {
            UsersListView(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                userList = userList,
                lastVisibleItemIndex = lastVisibleItemIndex,
                onItemClick = onItemClick,
                onListScrolledToEnd = {
                    // last item is visible
                    onListScrolledToEnd(it)
                }
            )
        }
    }
}

@Composable
fun SearchBar(
    onSearchSubmitted: (String) -> Unit,
    onClearInput: () -> Unit
) {
    // keep search text across screen rotation etc.
    var searchText by rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(36.dp))
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        TextField(
            trailingIcon = {
                Icon(Icons.Filled.Clear, "", tint = MaterialTheme.colorScheme.surfaceTint,
                    modifier = Modifier.clickable {
                        searchText = ""
                        onClearInput()
                    }
                )
            },
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedTextColor = MaterialTheme.colorScheme.surface,
                // remove underline
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            value = searchText,
            onValueChange = { searchText = it },
            label = {
                Text(
                    stringResource(R.string.user_search_field_help),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 0.dp)
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchSubmitted(searchText)
                    keyboardController?.hide()
                }
            ),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}


@Preview(showBackground = true)
@Composable
fun UserListScreenContentPreview() {
    GithubCruiseTheme {
        UsersListScreenContent(
            isLoading = false,
            userList = emptyList(),
            lastVisibleItemIndex = 0,
            errorMessage = "Error",
            onItemClick = { }, onSearchSubmitted = {},
            onClearInput = {},
            onListScrolledToEnd = {},
        )
    }
}

