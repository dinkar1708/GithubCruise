package com.jetpack.compose.github.github.cruise.ui.features.users

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpack.compose.github.github.cruise.domain.model.User
import com.jetpack.compose.github.github.cruise.ui.shared.NetworkImageView
import com.jetpack.compose.github.github.cruise.ui.theme.GithubCruiseTheme

/**
 * Created by Dinakar Maurya on 2024/05/13.
 */
@Composable
fun UsersListView(
    modifier: Modifier, userList: List<User>, onItemClick: (User) -> Unit
) {
    LazyColumn(
        modifier = modifier.background(MaterialTheme.colorScheme.background)
    ) {
        items(userList.size) { index ->
            UserListItem(user = userList[index], onItemClick = onItemClick)
        }
    }
}

@Composable
fun UserListItem(user: User, onItemClick: (User) -> Unit) {
    Box(
        modifier = Modifier
            .padding(1.dp)
            .clickable { onItemClick(user) }
    ) {
        NetworkImageView(
            modifier = Modifier.size(size = 60.dp),
            imageUrl = user.avatarUrl,
            contentDescription = "Profile picture of ${user.login}"
        )
        Column {
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
        UsersListView(
            modifier = Modifier
                .fillMaxWidth(),
            userList = users,
        ) {}
    }
}