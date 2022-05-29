package com.ac.musicac.domain.releases

data class Release(
    val albumType: String,
    val artists: List<Artist>,
    val copyrights: List<Copyright>,
    val externalIds: ExternalIds,
    val externalUrls: ExternalUrls,
    val href: String,
    val id: String,
    val image: Image?,
    val label: String,
    val name: String,
    val popularity: Int,
    val releaseDate: String,
    val release_date_precision: String,
    val total_tracks: Int,
    val tracks: Tracks,
    val type: String,
    val uri: String
)

data class Tracks(
    val href: String,
    val items: List<Track>,
    val limit: Int?,
    val next: Int?,
    val offset: Int?,
    val previous: Int?,
    val total: Int,
)

data class Track(
    val artists: List<Artist>,
    val disc_number: Int,
    val duration_ms: Int,
    val explicit: Boolean,
    val external_urls: ExternalUrls,
    val href: String,
    val id: String,
    val is_local: Boolean,
    val is_playable: Boolean,
    val name: String,
    val preview_url: String? = "",
    val track_number: String,
    val type: String,
    val uri: String
)

data class Artist(
    val external_urls: ExternalUrls,
    val href: String,
    val id: String,
    val name: String,
    val type: String,
    val uri: String
)

data class Copyright(
    val text: String
)

data class ExternalIds(
    val upc: String
)

