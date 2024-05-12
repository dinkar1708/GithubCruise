package com.jetpack.compose.github.github.cruise

import android.app.Application
import com.jetpack.compose.github.github.cruise.BuildConfig.DEBUG
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created by Dinakar Maurya on 2024/05/12.
 */
@HiltAndroidApp
class GithubCruiseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}