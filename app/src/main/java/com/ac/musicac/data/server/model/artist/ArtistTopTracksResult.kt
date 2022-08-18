package com.ac.musicac.data.server.model.artist
import com.ac.musicac.data.server.model.releases.ArtistResult
import com.ac.musicac.data.server.model.releases.ExternalUrlsResult
import com.ac.musicac.data.server.model.releases.ImageResult
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class ArtistTopTrackResult(
    @SerializedName("tracks") val tracks: List<TopTracksResult>
)

@Serializable
data class TopTracksResult(
    @SerializedName("album") val album: TopTrackAlbumResult,
    @SerializedName("artists") val artists: List<ArtistResult>,
    @SerializedName("available_markets") val availableMarkets: List<String>,
    @SerializedName("disc_number") val discNumber: Int,
    @SerializedName("duration_ms") val durationMs: Int,
    @SerializedName("explicit") val explicit: Boolean,
    @SerializedName("external_ids") val externalIds: ExternalIdsResultX,
    @SerializedName("external_urls") val externalUrls: ExternalUrlsResult,
    @SerializedName("href") val href: String,
    @SerializedName("id") val id: String,
    @SerializedName("is_local") val isLocal: Boolean,
    @SerializedName("is_playable") val isPlayable: Boolean,
    @SerializedName("linked_from") val linkedFrom: LinkedFromResult,
    @SerializedName("name") val name: String,
    @SerializedName("popularity") val popularity: Int,
    @SerializedName("preview_url") val previewUrl: String,
    @SerializedName("restrictions") val restrictions: RestrictionsResult,
    @SerializedName("track_number") val trackNumber: Int,
    @SerializedName("type") val type: String,
    @SerializedName("uri") val uri: String
)

@Serializable
data class TopTrackAlbumResult(
    @SerializedName("album_group") val albumGroup: String,
    @SerializedName("album_type") val albumType: String,
    @SerializedName("artists") val artists: List<ArtistResult>,
    @SerializedName("available_markets") val availableMarkets: List<String>,
    @SerializedName("external_urls") val externalUrls: ExternalUrlsResult,
    @SerializedName("href") val href: String,
    @SerializedName("id") val id: String,
    @SerializedName("images") val images: List<ImageResult>,
    @SerializedName("name") val name: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("release_date_precision") val releaseDatePrecision: String,
    @SerializedName("restrictions") val restrictions: RestrictionsResult,
    @SerializedName("total_tracks") val totalTracks: Int,
    @SerializedName("type") val type: String,
    @SerializedName("uri") val uri: String
)

@Serializable
data class ExternalIdsResultX(
    @SerializedName("ean") val ean: String,
    @SerializedName("isrc") val isrc: String,
    @SerializedName("upc") val upc: String
)

@Serializable
data class LinkedFromResult(
    @SerializedName("album")
    val album: TopTrackAlbumResult,
    @SerializedName("artists")
    val artists: List<ArtistViewResult>,
    @SerializedName("available_markets")
    val availableMarkets: List<String>,
    @SerializedName("disc_number")
    val discNumber: Int,
    @SerializedName("duration_ms")
    val durationMs: Int,
    @SerializedName("explicit")
    val explicit: Boolean,
    @SerializedName("external_ids")
    val externalIds: ExternalIdsResultX,
    @SerializedName("external_urls")
    val externalUrls: ExternalUrlsResult,
    @SerializedName("href")
    val href: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("is_local")
    val isLocal: Boolean,
    @SerializedName("is_playable")
    val isPlayable: Boolean,
    @SerializedName("linked_from")
    val linkedFrom: LinkedFromResult,
    @SerializedName("name")
    val name: String,
    @SerializedName("popularity")
    val popularity: Int,
    @SerializedName("preview_url")
    val previewUrl: String,
    @SerializedName("restrictions")
    val restrictions: RestrictionsResult,
    @SerializedName("track_number")
    val trackNumber: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("uri")
    val uri: String
)

@Serializable
data class RestrictionsResult(
    @SerializedName("reason")
    val reason: String
)