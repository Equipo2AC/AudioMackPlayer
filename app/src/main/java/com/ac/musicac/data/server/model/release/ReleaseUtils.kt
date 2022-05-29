package com.ac.musicac.data.server.model.release

fun getArtistsName(artists: List<RemoteArtist>): String {

    val names: MutableList<String> = mutableListOf()

    artists.map {
        names.add(it.name)
    }

    return names.joinToString(", ")
}