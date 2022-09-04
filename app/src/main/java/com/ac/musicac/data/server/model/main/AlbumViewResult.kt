package com.ac.musicac.data.server.model.main

import com.ac.musicac.data.server.model.releases.ExternalUrlsResult
import com.ac.musicac.data.server.model.releases.ImageResult
import com.ac.musicac.data.server.model.releases.TracksResult
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class AlbumViewResult(
    @SerializedName("album_type") val album_type: String,
    @SerializedName("artists") val artists: List<ArtistViewResult>?,
    @SerializedName("available_markets") val available_markets: List<String>,
    @SerializedName("external_urls") val external_urls: ExternalUrlsResult,
    @SerializedName("href") val href: String,
    @SerializedName("id") val id: String,
    @SerializedName("images") val images: List<ImageResult>,
    @SerializedName("name") val name: String,
    @SerializedName("release_date") val release_date: String,
    @SerializedName("release_date_precision") val release_date_precision: String,
    @SerializedName("restrictions") val restrictions: Restrictions?,
    @SerializedName("total_tracks") val total_tracks: Int,
    @SerializedName("tracks") val tracks: TracksResult?,
    @SerializedName("type") val type: String,
    @SerializedName("uri") val uri: String
)

@Serializable
data class Restrictions(
    @SerializedName("reason")
    val reason: String
)


