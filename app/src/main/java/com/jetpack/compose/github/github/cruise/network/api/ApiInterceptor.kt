package com.jetpack.compose.github.github.cruise.network.api

import com.jetpack.compose.github.github.cruise.network.model.ApiError
import com.jetpack.compose.github.github.cruise.network.model.ApiErrorResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by Dinakar Maurya on 2024/05/12.
 */
class ApiInterceptor(private val moshi: Moshi) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        try {
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
        }catch (exception: UnknownHostException){
            throw ApiError.NetworkError(exception.message ?: "Unknown host error")
        }
    }

    private fun handleError(responseBody: ResponseBody) {
        try {
            val adapter: JsonAdapter<ApiErrorResponse> = moshi.adapter(ApiErrorResponse::class.java)
            val errorResponse = adapter.fromJson(responseBody.string())
            errorResponse?.let {
                throw ApiError.ApiException(it)
            } ?: run {
                throw ApiError.UnknownError
            }
        } catch (timeoutException: SocketTimeoutException) {
            throw ApiError.TimeoutError(timeoutException.message ?: "Timeout error")
        } catch (ioException: IOException) {
            throw ApiError.NetworkError(ioException.message ?: "Unknown network error")
        } catch (exception: Exception) {
            throw ApiError.UnknownError
        }
    }


}