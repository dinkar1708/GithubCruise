package com.jetpack.compose.github.github.cruise.ui.shared

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

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