package com.ac.musicac.data.server.model.main

import com.ac.musicac.data.server.model.releases.ExternalUrlsResult
import com.ac.musicac.data.server.model.releases.ImageResult
import com.ac.musicac.data.server.model.releases.TracksResult
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class AlbumViewResult(
    @SerializedName("album_type") val albumType: String,
    @SerializedName("artists")
    val artists: List<ArtistViewResult>,
    @SerializedName("available_markets")
    val availableMarkets: List<String>,
    @SerializedName("external_urls")
    val externalUrls: ExternalUrlsResult,
    @SerializedName("href")
    val href: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("images")
    val images: List<ImageResult>,
    @SerializedName("name")
    val name: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("release_date_precision")
    val releaseDatePrecision: String,
    @SerializedName("restrictions")
    val restrictions: Restrictions,
    @SerializedName("total_tracks")
    val totalTracks: Int,
    @SerializedName("tracks")
    val tracks: TracksResult,
    @SerializedName("type")
    val type: String,
    @SerializedName("uri")
    val uri: String
)

@Serializable
data class Restrictions(
    @SerializedName("reason")
    val reason: String
)


