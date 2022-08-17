package com.ac.musicac.domain

data class PopularArtist(
    val albumType: String,
    val artists: List<Artist>,
    val availableMarkets: List<String>,
    val externalUrls: ExternalUrlsX,
    val href: String,
    val id: String,
    val image: Image?,
    val name: String,
    val releaseDate: String,
    val releaseDatePrecision: String,
    val totalTracks: Int,
    val type: String,
    val uri: String,
    val followers: Int,
    val genres: List<String>
)
