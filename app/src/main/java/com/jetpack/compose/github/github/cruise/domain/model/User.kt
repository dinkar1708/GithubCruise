package com.jetpack.compose.github.github.cruise.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Dinakar Maurya on 2024/05/12
 */
@JsonClass(generateAdapter = true)
data class User(
    val id: Long = 0,
    val login: String = "",
    val type: String = "",
    val url: String = "",
    val score: Double = 0.0,
    @Json(name = "avatar_url")
    val avatarUrl: String = "",
)
