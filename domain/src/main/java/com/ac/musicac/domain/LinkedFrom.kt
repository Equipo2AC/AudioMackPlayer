package com.ac.musicac.domain

data class LinkedFrom(
    val album: TopTrackAlbum,
    val artists: List<Artist>,
    val available_markets: List<String>,
    val disc_number: Int,
    val duration_ms: Int,
    val explicit: Boolean,
    val external_ids: ExternalIds,
    val external_urls: ExternalUrls,
    val href: String,
    val id: String,
    val is_local: Boolean,
    val is_playable: Boolean,
    val linked_from: LinkedFrom?,
    val name: String,
    val popularity: Int,
    val preview_url: String,
    val restrictions: Restrictions,
    val track_number: Int,
    val type: String,
    val uri: String
)
