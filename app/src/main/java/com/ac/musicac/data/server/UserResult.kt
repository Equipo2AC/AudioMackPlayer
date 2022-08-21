package com.ac.musicac.data.server

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResult(
    val id: String,
    val country: String?,
    @SerialName("display_name")
    val displayName: String?,
    val email: String,
    @SerialName("explicit_content")
    val explicitContent: UserExplicitContent?,
    @SerialName("external_urls")
    val externalUrls: UserExternalUrls,
    val followers: UserFollowers,
    val href: String,
    val images: List<UserImage>,
    val product: String?,
    val type: String,
    val uri: String
)

@Serializable
data class UserImage(
    val height: Int,
    val url: String,
    val width: Int
)

@Serializable
data class UserExplicitContent(
    @SerialName("filter_enabled")
    val filterEnabled: Boolean,
    @SerialName("filter_locked")
    val filterLocked: Boolean
)

@Serializable
data class UserExternalUrls(
    val spotify: String
)

@Serializable
data class UserFollowers(
    val href: String,
    val total: Int
)
