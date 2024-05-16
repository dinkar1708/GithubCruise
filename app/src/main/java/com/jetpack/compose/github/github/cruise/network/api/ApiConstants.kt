package com.jetpack.compose.github.github.cruise.network.api

import com.jetpack.compose.github.github.cruise.BuildConfig

/**
 * Created by Dinakar Maurya on 2024/05/15.
 */
class ApiConstants {
    companion object {
        val BASE_URL: String = BuildConfig.API_BASE_URL
        val API_VERSION: String = BuildConfig.API_VERSION
    }
}