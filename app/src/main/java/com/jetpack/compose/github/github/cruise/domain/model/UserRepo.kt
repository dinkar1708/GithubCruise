package com.jetpack.compose.github.github.cruise.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Dinakar Maurya on 2024/05/14.
 */

@JsonClass(generateAdapter = true)
data class UserRepo(
    val id: Long = 0,
    val name: String = "",
    // can be null as per api doc
    val language: String? = "",
    @Json(name = "stargazers_count")
    val stargazersCount: String = "",
    // can be null as per api doc
    val description: String? = "",
    @Json(name = "full_name")
    val fullName: String = "",
    @Json(name = "html_url")
    val htmlUrl: String = "",
    val fork: Boolean,
)