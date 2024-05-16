package com.jetpack.compose.github.github.cruise.ui.features.userrepository.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpack.compose.github.github.cruise.R
import com.jetpack.compose.github.github.cruise.domain.model.UserProfile
import com.jetpack.compose.github.github.cruise.ui.shared.NetworkImageView
import com.jetpack.compose.github.github.cruise.ui.theme.GithubCruiseTheme

/**
 * Created by Dinakar Maurya on 2024/05/14.
 */

@Composable
fun UserProfileView(userProfile: UserProfile) {
    Row(
        Modifier
            .shadow(10.dp, RoundedCornerShape(8.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primaryContainer,
                        MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                ), shape = RoundedCornerShape(4.dp)
            )
            .padding(all = 16.dp)
    ) {
        NetworkImageView(
            modifier = Modifier
                .size(size = 100.dp)
                .padding(start = 0.dp),
            imageUrl = userProfile.avatarUrl,
            contentDescription = stringResource(
                R.string.profile_picture_off_icon_content_desc,
                userProfile.login
            )
        )

        Column(Modifier.padding(start = 16.dp, top = 16.dp)) {
            Text(
                text = userProfile.name ?: "",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.background
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
                        style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.background),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = stringResource(R.string.user_repository_profile_followers),
                        style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.background),
                    )
                }

                Column(
                    Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = "${userProfile.following}",
                        style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.background),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = stringResource(R.string.user_repository_profile_following),
                        style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.background),
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
