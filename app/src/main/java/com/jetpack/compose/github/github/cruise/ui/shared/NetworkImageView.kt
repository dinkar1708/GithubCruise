package com.jetpack.compose.github.github.cruise.ui.shared

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.jetpack.compose.github.github.cruise.ui.theme.GithubCruiseTheme

/**
 * Created by Dinakar Maurya on 2024/05/13.
 */
@Composable
fun NetworkImageView(
    imageUrl: String?,
    contentDescription: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop
) {
    imageUrl?.let {
        AsyncImage(
            model = imageUrl,
            contentDescription = contentDescription,
            modifier = modifier.clip(CircleShape),
            contentScale = contentScale
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NetworkImageViewPreview() {
    GithubCruiseTheme {
        Surface {
            NetworkImageView(imageUrl = "", contentDescription = "desc")
        }
    }
}