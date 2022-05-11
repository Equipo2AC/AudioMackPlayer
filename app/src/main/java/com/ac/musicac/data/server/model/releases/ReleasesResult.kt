package com.ac.musicac.data.server.model.releases

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ReleasesResult(
    @SerializedName("albums") val albums: AlbumsResult
)

@Serializable
data class AlbumsResult(
    @SerializedName("href") val href: String,
    @SerializedName("items") val items: List<ItemResult>,
    @SerializedName("limit") val limit: Int,
    @SerializedName("next") val next: String,
    @SerializedName("offset") val offset: Int,
    @SerializedName("previous") val previous: String? = "",
    @SerializedName("total") val total: Int
)

@Serializable
data class ItemResult(
    @SerializedName("album_type") val album_type: String,
    @SerializedName("artists") val artists: List<ArtistResult>,
    @SerializedName("available_markets") val available_markets: List<String>,
    @SerializedName("external_urls") val external_urls: ExternalUrlsXResult,
    @SerializedName("href") val href: String,
    @SerializedName("id") val id: String,
    @SerializedName("images") val images: List<ImageResult>,
    @SerializedName("name") val name: String,
    @SerializedName("release_date") val release_date: String,
    @SerializedName("release_date_precision") val release_date_precision: String,
    @SerializedName("total_tracks") val total_tracks: Int,
    @SerializedName("type") val type: String,
    @SerializedName("uri") val uri: String
)

@Serializable
data class ArtistResult(
    @SerializedName("external_urls") val external_urls: ExternalUrlsResult,
    @SerializedName("href") val href: String,
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("type") val type: String,
    @SerializedName("uri") val uri: String
)

@Serializable
data class ImageResult(
    @SerializedName("height") val height: Int,
    @SerializedName("url") val url: String,
    @SerializedName("width") val width: Int
)

@Serializable
data class ExternalUrlsXResult(
    @SerializedName("spotify") val spotify: String
)

@Serializable
data class ExternalUrlsResult(
    @SerializedName("spotify") val spotify: String
)