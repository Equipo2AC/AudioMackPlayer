package com.ac.musicac.domain.releases

data class Release(
    val albumType: String,
    val artists: List<Artist>,
    val copyrights: List<Copyright>,
    val id: String,
    val image: Image?,
    val label: String,
    val name: String,
    val popularity: Int,
    val releaseDate: String,
    val totalTracks: String,
    val tracks: Tracks,
)

data class Tracks(
    val items: List<Track>,
    val total: Int,
)

data class Track(
    val artists: List<Artist>,
    val discNumber: Int,
    val durationMs: Int,
    val id: String,
    val name: String,
    val trackNumber: String,
)

data class Artist(
    val id: String,
    val name: String
)

data class Copyright(
    val text: String
)

data class ExternalIds(
    val upc: String
)

