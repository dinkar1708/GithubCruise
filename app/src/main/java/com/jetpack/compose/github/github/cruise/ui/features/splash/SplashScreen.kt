package com.jetpack.compose.github.github.cruise.ui.features.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.jetpack.compose.github.github.cruise.R
import com.jetpack.compose.github.github.cruise.ui.MainDestinations.USERS_LIST_SCREEN_ROUTE
import com.jetpack.compose.github.github.cruise.ui.theme.DarkColor
import com.jetpack.compose.github.github.cruise.ui.theme.MediumDarkColor
import kotlinx.coroutines.delay

/**
 * Created by Dinakar Maurya on 2024/05/16.
 */
@Composable
fun SplashScreen(navController: NavController) {
    var isTextAnimating by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = isTextAnimating) {
        delay(3000)
        isTextAnimating = false
        // close splash screen
        navController.popBackStack()
        // start from user list
        navController.navigate(USERS_LIST_SCREEN_ROUTE)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    radius = 1500f,
                    // same color for dark and light themes
                    colors = listOf(DarkColor, MediumDarkColor),
                ),
            ),
        contentAlignment = Alignment.Center
    ) {
        val scale = remember { Animatable(initialValue = if (isTextAnimating) 0.2f else 1.0f) }

        LaunchedEffect(isTextAnimating) {
            scale.animateTo(
                if (isTextAnimating) 1.0f else 0.5f,
                animationSpec = tween(durationMillis = 3000)
            )
        }

        Text(
            stringResource(R.string.splash_animation_github_cruise),
            color = Color.White,
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.scale(scale.value)
        )
    }
}
