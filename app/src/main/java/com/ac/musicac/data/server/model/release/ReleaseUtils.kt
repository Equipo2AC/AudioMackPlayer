package com.ac.musicac.data.server.model.release

import com.ac.musicac.data.server.model.releases.ArtistResult

fun getArtistsName(artists: List<ArtistResult>): String {

    val names: MutableList<String> = mutableListOf()

    artists.map {
        names.add(it.name)
    }

    return names.joinToString(", ")
}