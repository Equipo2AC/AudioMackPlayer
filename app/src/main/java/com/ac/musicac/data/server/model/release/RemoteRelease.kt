package com.ac.musicac.data.server.model.release

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteRelease(
    @SerializedName("album_type") val album_type: String,
    @SerializedName("artists") val artists: List<RemoteArtist>,
    @SerializedName("copyrights") val copyrights: List<RemoteCopyright>,
    @SerializedName("external_ids") val external_ids: RemoteExternalIds,
    @SerializedName("external_urls") val external_urls: RemoteExternalUrls,
    @SerializedName("genres") val genres: List<String>? = listOf(),
    @SerializedName("href") val href: String,
    @SerializedName("id") val id: String,
    @SerializedName("images") val images: List<RemoteImage>,
    @SerializedName("label") val label: String,
    @SerializedName("name") val name: String,
    @SerializedName("popularity") val popularity: Int,
    @SerializedName("release_date") val release_date: String,
    @SerializedName("release_date_precision") val release_date_precision: String,
    @SerializedName("total_tracks") val total_tracks: Int,
    @SerializedName("tracks") val tracks: RemoteTracks,
    @SerializedName("type") val type: String,
    @SerializedName("uri") val uri: String,
)

@Serializable
data class RemoteTracks(
    @SerializedName("href") val href: String,
    @SerializedName("items") val items: List<RemoteTrack>,
    @SerializedName("limit") val limit: Int?,
    @SerializedName("next") val next: Int? = 0,
    @SerializedName("offset") val offset: Int? = 0,
    @SerializedName("previous") val previous: Int? = 0,
    @SerializedName("total") val total: Int,
)

@Serializable
data class RemoteTrack(
    @SerializedName("artists") val artists: List<RemoteArtist>,
    @SerializedName("disc_number") val disc_number: Int,
    @SerializedName("duration_ms") val duration_ms: Int,
    @SerializedName("explicit") val explicit: Boolean,
    @SerializedName("external_urls") val external_urls: RemoteExternalUrls,
    @SerializedName("href") val href: String,
    @SerializedName("id") val id: String,
    @SerializedName("is_local") val is_local: Boolean,
    @SerializedName("is_playable") val is_playable: Boolean,
    @SerializedName("name") val name: String,
    @SerializedName("preview_url") val preview_url: String?,
    @SerializedName("track_number") val track_number: Int,
    @SerializedName("type") val type: String,
    @SerializedName("uri") val uri: String,
)

@Serializable
data class RemoteExternalIds(
    @SerializedName("upc") val upc: String,
)

@Serializable
data class RemoteCopyright(
    @SerializedName("text") val text: String,
    @SerializedName("type") val type: String,
)

