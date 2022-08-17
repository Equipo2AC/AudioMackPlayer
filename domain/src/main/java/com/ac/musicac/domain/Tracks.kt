package com.ac.musicac.domain

data class Tracks(
    val items: List<Track>,
    val total: Int
)

data class PopularTracks(
    val items: List<PopularTrack>,
    val total: Int
)
