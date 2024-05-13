package com.jetpack.compose.github.github.cruise.network.api

import com.jetpack.compose.github.github.cruise.domain.model.SearchUser
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Dinakar Maurya on 2024/05/12
 */
interface APIInterface {
    // search apis
    @GET("/search/users")
    suspend fun getSearchUsers(
        @Query("q") userName: String, @Query("page") page: Int,
        @Query("per_page") pageSize: Int,
    ): SearchUser
}