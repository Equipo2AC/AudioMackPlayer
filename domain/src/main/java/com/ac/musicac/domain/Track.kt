package com.ac.musicac.domain

data class Track(
    val artists: String?,
    val discNumber: Int,
    val durationMs: Int,
    val id: String,
    val name: String,
    val trackNumber: String
)
