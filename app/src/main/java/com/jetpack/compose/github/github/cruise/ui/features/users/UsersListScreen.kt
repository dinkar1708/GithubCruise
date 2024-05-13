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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jetpack.compose.github.github.cruise.domain.model.User
import com.jetpack.compose.github.github.cruise.ui.shared.AppActionBarView
import com.jetpack.compose.github.github.cruise.ui.shared.StateContentBox
import com.jetpack.compose.github.github.cruise.ui.theme.GithubCruiseTheme

/**
 * Created by Dinakar Maurya on 2024/05/12.
 */
@Composable
fun UsersListScreen(
    viewModel: UsersListViewModel,
    onClick: (User) -> Unit
) {
    val viewState by viewModel.uiState.collectAsStateWithLifecycle()

    UsersListScreenContent(
        isLoading = viewState.isLoading,
        userList = viewState.userList,
        errorMessage = viewState.errorMessage,
        onItemClick = onClick,
        onSearchSubmitted = { viewModel.updateInputString(it) },
        onClearInput = {}
    )
}

@Composable
fun UsersListScreenContent(
    isLoading: Boolean,
    userList: List<User>,
    errorMessage: String,
    onItemClick: (User) -> Unit,
    onSearchSubmitted: (String) -> Unit,
    onClearInput: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {

        AppActionBarView(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            headerText = "Users",
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
                onItemClick = onItemClick
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    onSearchSubmitted: (String) -> Unit,
    onClearInput: () -> Unit
) {
    var searchText by remember { mutableStateOf("") }

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
            // This material API is experimental and is likely to change or to be removed in the future.
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            value = searchText,
            onValueChange = { searchText = it },
            label = {
                Text(
                    "Enter user name",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 0.dp)
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = { onSearchSubmitted(searchText) }
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
            errorMessage = "Error",
            onItemClick = { }, onSearchSubmitted = {},
            onClearInput = {}
        )
    }
}