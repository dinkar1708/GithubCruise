package com.jetpack.compose.github.github.cruise.ui.shared

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Created by Dinakar Maurya on 2024/05/17.
 */
@Composable
fun HorizontalLineView(modifier: Modifier = Modifier) {
    HorizontalDivider(
        modifier = modifier
            .fillMaxWidth(),
        color = MaterialTheme.colorScheme.onBackground
    )
}