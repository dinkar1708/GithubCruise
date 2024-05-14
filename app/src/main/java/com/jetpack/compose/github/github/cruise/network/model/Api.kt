package com.jetpack.compose.github.github.cruise.network.model

import java.io.IOException

/**
 * Created by Dinakar Maurya on 2024/05/14.
 */
class ApiException(val errorResponse: ApiErrorResponse) : IOException(errorResponse.message)
