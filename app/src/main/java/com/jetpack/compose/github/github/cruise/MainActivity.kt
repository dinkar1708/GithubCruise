package com.jetpack.compose.github.github.cruise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
                GithubCruiseRootComposable()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GithubCruiseTheme {
        Greeting("Android")
    }
}