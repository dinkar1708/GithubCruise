package com.jetpack.compose.github.github.cruise.network.api

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by Dinakar Maurya on 2024/05/12.
 */
class ApiInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            // v3 api
            .addHeader("X-GitHub-Api-Version", "2022-11-28")
            .build()
        return chain.proceed(request)
    }
}