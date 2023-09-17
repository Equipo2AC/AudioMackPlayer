package com.ac.musicac.domain

data class Item(
    val id: String,
    val albumType: String,
    val artists: String,
    val availableMarkets: List<String>,
    val externalUrls: ExternalUrls,
    val href: String,
    val itemId: String,
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
