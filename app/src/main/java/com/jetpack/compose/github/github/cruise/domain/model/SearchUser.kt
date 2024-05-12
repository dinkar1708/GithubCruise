package com.jetpack.compose.github.github.cruise.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Dinakar Maurya on 2024/05/12
 */
@JsonClass(generateAdapter = true)
data class SearchUser(
    @Json(name = "total_count")
    val totalCount: Int,
    @Json(name = "incomplete_results")
    val incompleteResults: Boolean,
    // use readable name
    @Json(name = "items")
    val users: List<User>
)