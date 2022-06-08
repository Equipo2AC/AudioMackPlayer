package com.ac.musicac.domain

data class Item(
    val album_type: String,
    val artists: String,
    val available_markets: List<String>,
    val external_urls: ExternalUrlsX,
    val href: String,
    val id: String,
    val image: Image?,
    val name: String,
    val release_date: String,
    val release_date_precision: String,
    val total_tracks: Int,
    val type: String,
    val uri: String,
    val followers: Int,
    val genres: List<String>
)
