package com.ac.musicac.domain

data class SeveralAlbums(
    val albums: List<AlbumView>
)

data class AlbumView(
    val id: Int,
    val album_type: String,
    val artists: List<Artist>?,
    val copyrights: List<Copyright>,
    val external_ids: ExternalIds,
    val external_urls: ExternalUrls,
    val genres: List<String>?,
    val href: String,
    val albumId: String,
    val image: String?,
    val label: String,
    val name: String,
    val popularity: Int,
    val release_date: String,
    val release_date_precision: String,
    val total_tracks: Int,
    val tracks: Tracks,
    val type: String,
    val uri: String
)

