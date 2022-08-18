package com.ac.musicac.data.server.model.artist

import com.ac.musicac.data.server.model.releases.*
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ArtistViewResult(
    // @SerializedName("external_urls") val externalUrls: ExternalUrlsResult,
    @SerializedName("external_urls") val external_urls: ExternalUrlsResult,
    @SerializedName("followers") val followers: FollowersResult,
    @SerializedName("genres") val genres: List<String>?,
    @SerializedName("href") val href: String,
    @SerializedName("id") val id: String,
    @SerializedName("images") val images: List<ImageResult>,
    @SerializedName("name") val name: String,
    @SerializedName("popularity") val popularity: Int?,
    @SerializedName("type") val type: String?,
    @SerializedName("uri") val uri: String
)

