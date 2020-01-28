package com.github.galcyurio.paginggithubsample.domain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @author galcyurio
 */
@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "login")
    val name: String,
    val id: Long
)