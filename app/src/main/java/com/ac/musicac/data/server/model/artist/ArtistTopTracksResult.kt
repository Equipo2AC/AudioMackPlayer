package com.ac.musicac.data.server.model.artist

import com.ac.musicac.data.server.model.releases.ArtistResult
import com.ac.musicac.data.server.model.releases.ExternalUrlsResult
import com.ac.musicac.data.server.model.releases.ImageResult
import com.ac.musicac.data.server.model.releases.ItemResult
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ArtistTopTrackResult(
    @SerializedName("tracks") val tracks: List<TopTracksResult>
)

@Serializable
data class TopTracksResult(
    @SerializedName("album") val album: List<TopTrackAlbumResult>,
    @SerializedName("artists") val artists: List<ArtistResult>
)

/*@Serializable
data class TopTracksResult(
    @SerializedName("album") val album: TopTrackAlbumResult,
    @SerializedName("artists") val artists: List<ArtistResult>,
    @SerializedName("available_markets") val available_markets: List<String>,
    @SerializedName("disc_number") val disc_number: Int,
    @SerializedName("duration_ms") val duration_ms: Int,
    @SerializedName("explicit") val explicit: Boolean,
    @SerializedName("external_ids") val external_ids: ExternalIdsResultX,
    @SerializedName("external_urls") val external_urls: ExternalUrlsResult,
    @SerializedName("href") val href: String,
    @SerializedName("id") val id: String,
    @SerializedName("is_local") val is_local: Boolean,
    @SerializedName("is_playable") val is_playable: Boolean,
    // @SerializedName("linked_from") val linked_from: LinkedFromResult?,
    @SerializedName("name") val name: String,
    @SerializedName("popularity") val popularity: Int,
    @SerializedName("preview_url") val preview_url: String,
    @SerializedName("restrictions") val restrictions: RestrictionsResult,
    @SerializedName("track_number") val track_number: Int,
    @SerializedName("type") val type: String,
    @SerializedName("uri") val uri: String
)*/

@Serializable
data class TopTrackAlbumResult(
    // @SerializedName("album_group") val album_group: String?,
    @SerializedName("album_type") val album_type: String,
    @SerializedName("artists") val artists: List<ArtistResult>,
    @SerializedName("available_markets") val available_markets: List<String>,
    @SerializedName("external_urls") val external_urls: ExternalUrlsResult,
    @SerializedName("href") val href: String,
    @SerializedName("id") val id: String,
    @SerializedName("images") val images: List<ImageResult>,
    @SerializedName("name") val name: String,
    @SerializedName("release_date") val release_date: String,
    @SerializedName("release_date_precision") val release_date_precision: String,
    @SerializedName("restrictions") val restrictions: RestrictionsResult,
    @SerializedName("total_tracks") val total_tracks: Int,
    @SerializedName("type") val type: String,
    @SerializedName("uri") val uri: String
)

@Serializable
data class ExternalIdsResultX(
    @SerializedName("ean") val ean: String?,
    @SerializedName("isrc") val isrc: String?,
    @SerializedName("upc") val upc: String?
)

@Serializable
data class LinkedFromResult(
    @SerializedName("album") val album: TopTrackAlbumResult,
    @SerializedName("artists") val artists: List<ArtistResult>,
    @SerializedName("available_markets") val available_markets: List<String>,
    @SerializedName("disc_number") val disc_number: Int,
    @SerializedName("duration_ms") val duration_ms: Int,
    @SerializedName("explicit") val explicit: Boolean,
    @SerializedName("external_ids") val external_ids: ExternalIdsResultX,
    @SerializedName("external_urls") val external_urls: ExternalUrlsResult,
    @SerializedName("href") val href: String,
    @SerializedName("id") val id: String,
    @SerializedName("is_local") val is_local: Boolean,
    @SerializedName("is_playable") val is_playable: Boolean,
    @SerializedName("linked_from") val linked_from: LinkedFromResult?,
    @SerializedName("name") val name: String,
    @SerializedName("popularity") val popularity: Int,
    @SerializedName("preview_url") val preview_url: String,
    @SerializedName("restrictions") val restrictions: RestrictionsResult,
    @SerializedName("track_number") val track_number: Int,
    @SerializedName("type") val type: String,
    @SerializedName("uri") val uri: String
)

@Serializable
data class RestrictionsResult(
    @SerializedName("reason")
    val reason: String
)