package com.jetpack.compose.github.github.cruise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.jetpack.compose.github.github.cruise.ui.GithubCruiseRootComposable
import com.jetpack.compose.github.github.cruise.ui.theme.GithubCruiseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
// https://developer.android.com/training/dependency-injection/hilt-android?_gl@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubCruiseTheme {
                // set background color for all view
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GithubCruiseRootComposable()
                }
            }
        }
    }
}