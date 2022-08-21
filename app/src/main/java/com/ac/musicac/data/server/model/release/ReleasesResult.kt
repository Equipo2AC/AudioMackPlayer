package com.ac.musicac.data.server.model.releases

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ReleasesResult(
    @SerializedName("albums") val albums: AlbumsResult
)

@Serializable
data class SearchResult(
    @SerializedName("albums") val albums: AlbumsResult?,
    @SerializedName("artists") val artists: ArtistsResult?
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
data class ArtistsResult(
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
    @SerializedName("album_type") val album_type: String?,
    @SerializedName("artists") val artists: List<ArtistResult>?,
    @SerializedName("available_markets") val available_markets: List<String>?,
    @SerializedName("external_urls") val external_urls: ExternalUrlsResult,
    @SerializedName("href") val href: String,
    @SerializedName("id") val id: String,
    @SerializedName("images") val images: List<ImageResult>,
    @SerializedName("name") val name: String,
    @SerializedName("release_date") val release_date: String?,
    @SerializedName("release_date_precision") val release_date_precision: String?,
    @SerializedName("total_tracks") val total_tracks: Int?,
    @SerializedName("type") val type: String,
    @SerializedName("uri") val uri: String,
    @SerializedName("followers") val followers: FollowersResult?,
    @SerializedName("genres") val genres: List<String>?
)

@Serializable
data class FollowersResult(
    @SerializedName("href") val href: String?,
    @SerializedName("total") val total: Int
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
data class RemoteRelease(
    @SerializedName("album_type") val album_type: String,
    @SerializedName("artists") val artists: List<ArtistResult>,
    @SerializedName("copyrights") val copyrights: List<CopyrightResult>,
    @SerializedName("external_ids") val external_ids: ExternalIdsResult,
    @SerializedName("external_urls") val external_urls: ExternalUrlsResult,
    @SerializedName("genres") val genres: List<String>? = listOf(),
    @SerializedName("href") val href: String,
    @SerializedName("id") val id: String,
    @SerializedName("images") val images: List<ImageResult>,
    @SerializedName("label") val label: String,
    @SerializedName("name") val name: String,
    @SerializedName("popularity") val popularity: Int,
    @SerializedName("release_date") val release_date: String,
    @SerializedName("release_date_precision") val release_date_precision: String,
    @SerializedName("total_tracks") val total_tracks: Int,
    @SerializedName("tracks") val tracks: TracksResult,
    @SerializedName("type") val type: String,
    @SerializedName("uri") val uri: String
)

@Serializable
data class TracksResult(
    @SerializedName("href") val href: String,
    @SerializedName("items") val items: List<TrackResult>,
    @SerializedName("limit") val limit: Int?,
    @SerializedName("next") val next: Int? = 0,
    @SerializedName("offset") val offset: Int? = 0,
    @SerializedName("previous") val previous: Int? = 0,
    @SerializedName("total") val total: Int
)

@Serializable
data class TrackResult(
    @SerializedName("artists") val artists: List<ArtistResult>,
    @SerializedName("disc_number") val disc_number: Int,
    @SerializedName("duration_ms") val duration_ms: Int,
    @SerializedName("explicit") val explicit: Boolean,
    @SerializedName("external_urls") val external_urls: ExternalUrlsResult,
    @SerializedName("href") val href: String,
    @SerializedName("id") val id: String,
    @SerializedName("is_local") val is_local: Boolean,
    @SerializedName("is_playable") val is_playable: Boolean,
    @SerializedName("name") val name: String,
    @SerializedName("preview_url") val preview_url: String?,
    @SerializedName("track_number") val track_number: Int,
    @SerializedName("type") val type: String,
    @SerializedName("uri") val uri: String
)

@Serializable
data class ExternalUrlsResult(
    @SerializedName("spotify") val spotify: String
)

@Serializable
data class ExternalIdsResult(
    @SerializedName("upc") val upc: String
)

@Serializable
data class CopyrightResult(
    @SerializedName("text") val text: String,
    @SerializedName("type") val type: String
)