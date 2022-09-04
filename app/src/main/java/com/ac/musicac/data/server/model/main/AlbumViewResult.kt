package com.ac.musicac.data.server.model.main

import com.ac.musicac.data.server.model.releases.*
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class AlbumViewResult(
    @SerializedName("album_type") val album_type: String,
    @SerializedName("artists") val artists: List<ArtistResult>?,
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
data class Restrictions(
    @SerializedName("reason")
    val reason: String
)




