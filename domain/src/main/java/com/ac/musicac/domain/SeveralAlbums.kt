package com.ac.musicac.domain

data class SeveralAlbums(
    val albums: List<AlbumView>
)

data class AlbumView(
    val id: Int,
    val album_type: String,
    val artists: List<Artist>?,
    val available_markets: List<String>,
    val external_urls: ExternalUrls,
    val href: String,
    val albumId: String,
    val image: String?,
    val name: String,
    val release_date: String,
    val release_date_precision: String,
    val restrictions: String?,
    val total_tracks: Int,
    val tracks: Tracks?,
    val type: String,
    val uri: String
)

