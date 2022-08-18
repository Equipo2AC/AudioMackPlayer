package com.ac.musicac.domain

data class TopTracks(
    val album: TopTrackAlbum,
    val artists: List<Artist>,
    val availableMarkets: List<String>,
    val discNumber: Int,
    val durationMs: Int,
    val explicit: Boolean,
    val externalIds: ExternalIds,
    val externalUrls: ExternalUrls,
    val href: String,
    val id: String,
    val isLocal: Boolean,
    val isPlayable: Boolean,
    val linkedFrom: LinkedFrom,
    val name: String,
    val popularity: Int,
    val previewUrl: String,
    val restrictions: Restrictions,
    val trackNumber: Int,
    val type: String,
    val uri: String
)
