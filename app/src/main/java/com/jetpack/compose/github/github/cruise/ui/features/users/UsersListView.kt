package com.jetpack.compose.github.github.cruise.ui.features.users

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpack.compose.github.github.cruise.domain.model.User
import com.jetpack.compose.github.github.cruise.ui.shared.NetworkImageView
import com.jetpack.compose.github.github.cruise.ui.theme.GithubCruiseTheme
import kotlinx.coroutines.flow.distinctUntilChanged
import timber.log.Timber

/**
 * Created by Dinakar Maurya on 2024/05/13.
 */
@Composable
fun UsersListView(
    modifier: Modifier,
    userList: List<User>,
    lastVisibleItemIndex: Int,
    onItemClick: (User) -> Unit,
    onListScrolledToEnd: (Int) -> Unit
) {
    val scrollState = rememberLazyListState()
    var scrolledToEnd by remember { mutableStateOf(false) }
    val TAG = "UsersListView"

    LazyColumn(
        modifier = modifier.background(MaterialTheme.colorScheme.background),
        state = scrollState
    ) {
        items(userList.size) { index ->
            UserListItem(user = userList[index], onItemClick = onItemClick)
        }
    }

    LaunchedEffect(scrollState) {
        snapshotFlow { scrollState.layoutInfo.visibleItemsInfo }
            .distinctUntilChanged()
            .collect { visibleItems ->
                if (visibleItems.lastOrNull()?.index == (userList.size - 1) && !scrolledToEnd) {
                    val lastVisibleItemIndex =
                        scrollState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
                    Timber.d("$TAG SET variable user scrolled to last page...lastVisibleItemIndex $lastVisibleItemIndex user size ${userList.size}")
                    // scrolled to ended true
                    scrolledToEnd = true
                    // Set a variable in the ViewModel to save it for the next recomposition.
                    onListScrolledToEnd(lastVisibleItemIndex)
                }
            }
    }
    // TODO Handle restoring the scroll position of the list after the next page data is loaded.
    LaunchedEffect(userList) {
        Timber.d("$TAG listen userList ...lastVisibleItemIndex $lastVisibleItemIndex user size ${userList.size} ")
    }
    LaunchedEffect(lastVisibleItemIndex) {
        Timber.d("$TAG listen lastVisibleItemIndex ...lastVisibleItemIndex $lastVisibleItemIndex user size ${userList.size} ")
        if (lastVisibleItemIndex > 0) {
            // This can scroll, but it is not accurate.
            scrollState.scrollToItem(lastVisibleItemIndex - 8)
            Timber.d("$TAG scrolled.......... ")
        }
    }
}


@Composable
fun UserListItem(user: User, onItemClick: (User) -> Unit) {
    Box(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .clickable { onItemClick(user) }
    ) {
        Row {

            NetworkImageView(
                modifier = Modifier.size(size = 60.dp),
                imageUrl = user.avatarUrl,
                contentDescription = "Profile picture of ${user.login}"
            )
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = user.login,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Text(
                    text = "Score ${user.score}",
                    style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurface)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserListPreview() {
    val users = mutableListOf(
        User(
            id = 1,
            login = "dinkar1708",
            type = "User",
            avatarUrl = "https://avatars.githubusercontent.com/u/14831652?v=4",
        ), User(
            id = 2,
            login = "dinkar1708",
            type = "User",
            avatarUrl = "https://avatars.githubusercontent.com/u/14831652?v=4",
        )
    )

    GithubCruiseTheme {
        UsersListView(modifier = Modifier.fillMaxWidth(), userList = users, onItemClick = {},
            onListScrolledToEnd = {}, lastVisibleItemIndex = 2
        )
    }
}
