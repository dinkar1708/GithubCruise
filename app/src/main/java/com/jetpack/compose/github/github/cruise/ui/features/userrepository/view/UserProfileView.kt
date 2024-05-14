package com.jetpack.compose.github.github.cruise.ui.features.userrepository.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpack.compose.github.github.cruise.domain.model.UserProfile
import com.jetpack.compose.github.github.cruise.ui.shared.NetworkImageView
import com.jetpack.compose.github.github.cruise.ui.theme.GithubCruiseTheme

/**
 * Created by Dinakar Maurya on 2024/05/14.
 */

@Composable
fun UserProfileView(userProfile: UserProfile) {
    Row {
        NetworkImageView(
            modifier = Modifier.size(size = 100.dp),
            imageUrl = userProfile.avatarUrl,
            contentDescription = "Profile picture of ${userProfile.login}"
        )

        Column(Modifier.padding(start = 16.dp, top = 16.dp)) {
            Text(
                text = userProfile.name ?: "",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                ),
            )
            Row(Modifier.padding(top = 16.dp)) {
                Column(
                    Modifier
                        .padding(end = 8.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = "${userProfile.followers}",
                        style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.onBackground),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "Followers",
                        style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.surfaceTint),
                    )
                }

                Column(
                    Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = "${userProfile.following}",
                        style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.onBackground),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "Following",
                        style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.surfaceTint),

                        )
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun UserProfileViewPreview() {
    val userProfile = UserProfile(
        id = 1,
        followers = 10,
        following = 20,
        name = "Dinakar Maurya",
        avatarUrl = "url",
        login = "dinkar1708"
    )

    GithubCruiseTheme {
        Surface {
            UserProfileView(
                userProfile = userProfile,
            )

        }
    }
}
