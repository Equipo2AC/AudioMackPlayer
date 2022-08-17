package com.ac.musicac.domain

data class Release(
    val albumType: String,
    val artists: String?,
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

/*data class Artist(
    val id: String,
    val name: String
)*/

