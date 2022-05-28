package com.ac.musicac.data.server.model.release

import com.google.gson.annotations.SerializedName

data class RemoteRelease(
    @SerializedName("album_type") val albumType: String,
    @SerializedName("artists") val artists: List<RemoteArtist>,
    @SerializedName("copyrights") val copyrights: List<RemoteCopyright>,
    @SerializedName("external_ids") val externalIds: List<RemoteExternalIds>,
    @SerializedName("external_urls") val externalUrls: List<RemoteExternalUrls>,
    @SerializedName("genres") val genres: List<String>,
    @SerializedName("href") val href: String,
    @SerializedName("id") val id: String,
    @SerializedName("images") val images: List<RemoteImage>,
    @SerializedName("label") val label: String,
    @SerializedName("name") val name: String,
    @SerializedName("popularity") val popularity: Int,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("release_date_precision") val release_date_precision: String,
    @SerializedName("total_tracks") val total_tracks: Int,
    @SerializedName("tracks") val tracks: List<RemoteTracks>,
    @SerializedName("type") val type: String,
    @SerializedName("uri") val uri: String,
)

data class RemoteTracks(
    @SerializedName("href") val href: String,
    @SerializedName("items") val items: List<RemoteTrack>,
    @SerializedName("limit") val limit: Int?,
    @SerializedName("next") val next: Int?,
    @SerializedName("offset") val offset: Int?,
    @SerializedName("previous") val previous: Int?,
    @SerializedName("total") val total: Int,
)

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
    @SerializedName("preview_url") val preview_url: String,
    @SerializedName("track_number") val track_number: Int,
    @SerializedName("type") val type: String,
    @SerializedName("uri") val uri: String,
)

data class RemoteExternalIds(
    @SerializedName("upc") val upc: String,
)

data class RemoteCopyright(
    @SerializedName("text") val text: String,
    @SerializedName("type") val type: String,
)

