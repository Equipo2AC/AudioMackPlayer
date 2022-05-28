package com.ac.musicac.data.server.model.release

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteReleases(
    @SerializedName("albums") val albums: RemoteAlbums
)

@Serializable
data class RemoteAlbums(
    @SerializedName("href") val href: String,
    @SerializedName("items") val items: List<RemoteItem>,
    @SerializedName("limit") val limit: Int,
    @SerializedName("next") val next: String,
    @SerializedName("offset") val offset: Int,
    @SerializedName("previous") val previous: String? = "",
    @SerializedName("total") val total: Int
)

@Serializable
data class RemoteItem(
    @SerializedName("album_type") val album_type: String,
    @SerializedName("artists") val artists: List<RemoteArtist>,
    @SerializedName("available_markets") val available_markets: List<String>,
    @SerializedName("external_urls") val external_urls: RemoteExternalUrls,
    @SerializedName("href") val href: String,
    @SerializedName("id") val id: String,
    @SerializedName("images") val images: List<RemoteImage>,
    @SerializedName("name") val name: String,
    @SerializedName("release_date") val release_date: String,
    @SerializedName("release_date_precision") val release_date_precision: String,
    @SerializedName("total_tracks") val total_tracks: Int,
    @SerializedName("type") val type: String,
    @SerializedName("uri") val uri: String
)

@Serializable
data class RemoteArtist(
    @SerializedName("external_urls") val external_urls: RemoteExternalUrls,
    @SerializedName("href") val href: String,
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("type") val type: String,
    @SerializedName("uri") val uri: String
)
