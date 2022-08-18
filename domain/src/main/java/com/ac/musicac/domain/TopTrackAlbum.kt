package com.ac.musicac.domain

data class TopTrackAlbum(
    val albumGroup: String,
    val albumType: String,
    val artists: List<Artist>,
    val availableMarkets: List<String>,
    val externalUrls: ExternalUrls,
    val href: String,
    val id: String,
    val images: List<Image>,
    val name: String,
    val releaseDate: String,
    val releaseDate_precision: String,
    val restrictions: Restrictions,
    val totalTracks: Int,
    val type: String,
    val uri: String
)
