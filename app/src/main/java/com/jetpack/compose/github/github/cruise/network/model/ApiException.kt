package com.jetpack.compose.github.github.cruise.network.model

import java.io.IOException

/**
 * Created by Dinakar Maurya on 2024/05/14.
 */
sealed class ApiError(override val message: String) : IOException(message) {
    class ApiException(errorResponse: ApiErrorResponse) : ApiError(errorResponse.message)
    class NetworkError(message: String) : ApiError(message)
    class TimeoutError(message: String) : ApiError(message)
    object UnknownError : ApiError("Unknown error occurred")
}
