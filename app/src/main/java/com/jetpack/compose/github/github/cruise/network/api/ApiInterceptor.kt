package com.jetpack.compose.github.github.cruise.network.api

import com.jetpack.compose.github.github.cruise.network.model.ApiError
import com.jetpack.compose.github.github.cruise.network.model.ApiErrorResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException

/**
 * Created by Dinakar Maurya on 2024/05/12.
 */
class ApiInterceptor(private val moshi: Moshi) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("X-GitHub-Api-Version", ApiConstants.GITHUB_API_VERSION_V3)
            .build()
        // parse response
        val response = chain.proceed(request)
        if (!response.isSuccessful) {
            response.body?.let { responseBody ->
                handleError(responseBody)
            }
        }
        return response
    }

    private fun handleError(responseBody: ResponseBody) {
        // handle more api error here
        val adapter: JsonAdapter<ApiErrorResponse> = moshi.adapter(ApiErrorResponse::class.java)
        val errorResponse = adapter.fromJson(responseBody.string())
        errorResponse?.let {
            throw ApiError.ApiException(it)
        }
    }
}