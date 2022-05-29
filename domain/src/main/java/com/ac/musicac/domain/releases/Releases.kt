package com.ac.musicac.domain.releases

data class Releases(
    val albums: Albums
)

data class Albums(
    val href: String,
    val items: List<Item>,
    val limit: Int,
    val next: String,
    val offset: Int,
    val previous: String? = "",
    val total: Int
)

data class Item(
    val album_type: String,
    val artists: String,
    val available_markets: List<String>,
    val external_urls: ExternalUrls,
    val href: String,
    val id: String,
    val image: Image?,
    val name: String,
    val release_date: String,
    val release_date_precision: String,
    val total_tracks: Int,
    val type: String,
    val uri: String
)